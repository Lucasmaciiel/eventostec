create table address(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    uf VARCHAR(2),
    event_id UUID NOT NULL,
    FOREIGN KEY(event_id) REFERENCES event(id) on DELETE CASCADE
);