CREATE TABLE plots(
    plot_id SERIAL PRIMARY KEY,
    name varchar(50),
    soil_type varchar(10)  not null
)