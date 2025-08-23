USE [mizan]
GO
--To fix this dates 
UPDATE [dbo].[export]
   SET [exported_date] = '2025-01-19'
  where ord_id = 21242
GO

UPDATE [dbo].[orders]
   SET [ord_date] = '2025-01-19'
  where ord_id = 21242
GO

-- to execute this function 
EXEC sp_ArchiveExportByYear
GO


CREATE OR ALTER PROCEDURE sp_ArchiveExportByYear
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @year INT, @currentYear INT;
    DECLARE @tableName NVARCHAR(100);
    DECLARE @sql NVARCHAR(MAX);

    -- Get current year
    SET @currentYear = YEAR(GETDATE());

    -- Safety check: adjust 2018 to the earliest valid year in your system
    IF @currentYear < 2018 OR @currentYear > YEAR(GETDATE()) + 1
    BEGIN
        PRINT '⚠️ System date seems invalid. Archiving skipped.';
        RETURN;
    END;

    -- Cursor over distinct years in the export table (excluding current year)
    DECLARE year_cursor CURSOR FOR
    SELECT DISTINCT YEAR(exported_date)
    FROM export
    WHERE YEAR(exported_date) < @currentYear;

    OPEN year_cursor;
    FETCH NEXT FROM year_cursor INTO @year;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SET @tableName = 'export_' + CAST(@year AS NVARCHAR(4));

        -- Step 1: Create archive table if not exists (copy empty structure)
        IF NOT EXISTS (SELECT 1 FROM sys.tables WHERE name = @tableName)
        BEGIN
            SET @sql = 'SELECT TOP 0 * INTO ' + QUOTENAME(@tableName) +
                       ' FROM export;';
            EXEC sp_executesql @sql;

            PRINT 'Created archive table ' + @tableName;
        END;

        -- Step 2: Insert data for that year (preserve exp_id values)
        SET @sql = '
            SET IDENTITY_INSERT ' + QUOTENAME(@tableName) + ' ON;

            INSERT INTO ' + QUOTENAME(@tableName) + ' 
                (exp_id, pro_id, cli_id, weight_, lot, inserted_date, exported_date,
                 num_of_con, pallet_numb, used, tot_wight, ord_id, empty_pack, storageID)
            SELECT exp_id, pro_id, cli_id, weight_, lot, inserted_date, exported_date,
                   num_of_con, pallet_numb, used, tot_wight, ord_id, empty_pack, storageID
            FROM export
            WHERE YEAR(exported_date) = ' + CAST(@year AS NVARCHAR) + ';

            SET IDENTITY_INSERT ' + QUOTENAME(@tableName) + ' OFF;';
        EXEC sp_executesql @sql;

        -- Step 3: Delete that year's data from main table
        SET @sql = 'DELETE FROM export WHERE YEAR(exported_date) = ' + CAST(@year AS NVARCHAR);
        EXEC sp_executesql @sql;

        PRINT 'Archived year ' + CAST(@year AS NVARCHAR) + ' into ' + @tableName;

        FETCH NEXT FROM year_cursor INTO @year;
    END;

    CLOSE year_cursor;
    DEALLOCATE year_cursor;

    PRINT '✅ Archive completed successfully. Export table now holds only current year data.';
END;
GO
