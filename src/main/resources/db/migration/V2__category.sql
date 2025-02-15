CREATE TABLE budgyscheme.category
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255),
    account_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

ALTER TABLE budgyscheme.category
    ADD CONSTRAINT FK_CATEGORY_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES budgyscheme.account (id);