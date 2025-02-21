CREATE TABLE IF NOT EXISTS fx_deals (
    deal_unique_id UUID PRIMARY KEY,
    from_currency VARCHAR(3) NOT NULL CHECK (char_length(from_currency) = 3),
    to_currency VARCHAR(3) NOT NULL CHECK (char_length(to_currency) = 3),
    deal_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deal_amount DECIMAL(15,2) NOT NULL CHECK (deal_amount > 0)
    );

INSERT INTO fx_deals (deal_unique_id, from_currency, to_currency, deal_timestamp, deal_amount) VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'USD', 'EUR', '2025-02-20 10:30:00', 1000.00),
    ('550e8400-e29b-41d4-a716-446655440001', 'GBP', 'USD', '2025-02-20 11:15:00', 750.50)
    ON CONFLICT (deal_unique_id) DO NOTHING;