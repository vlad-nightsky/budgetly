CREATE TABLE event_publication
(
    id               UUID PRIMARY KEY,
    completion_date  TIMESTAMP,
    event_type       VARCHAR(255) NOT NULL,
    listener_id      VARCHAR(255) NOT NULL,
    publication_date TIMESTAMP    NOT NULL,
    serialized_event TEXT         NOT NULL
);