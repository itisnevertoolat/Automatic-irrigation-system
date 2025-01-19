CREATE TABLE irrigation_schedules(
    schedule_id SERIAL PRIMARY KEY,
    start_time TIME NOT NULL,
    duration TIME NOT NULL,
    water_amount DECIMAL(10,2)  NOT NULL,
    plot_id int

)