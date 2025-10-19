ALTER TABLE budgyscheme.tbank_import
    ADD filtered BIGINT;

ALTER TABLE budgyscheme.tbank_import
    ADD parsed BIGINT;

ALTER TABLE budgyscheme.tbank_transaction
    ADD parse_result SMALLINT;

ALTER TABLE budgyscheme.tbank_import
    DROP COLUMN saved;

ALTER TABLE budgyscheme.tbank_import
    DROP COLUMN skipped;

ALTER TABLE budgyscheme.tbank_import
    ADD saved BIGINT;

ALTER TABLE budgyscheme.tbank_import
    ADD skipped BIGINT;