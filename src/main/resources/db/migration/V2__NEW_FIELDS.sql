-- 1. Добавляем новые колонки как nullable
ALTER TABLE tbank_import
    ADD COLUMN saved INTEGER;

ALTER TABLE tbank_import
    ADD COLUMN skipped INTEGER;

-- 2. Заполняем существующие записи значением 0
UPDATE tbank_import
SET saved   = 0,
    skipped = 0;