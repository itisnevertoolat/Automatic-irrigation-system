ALTER TABLE irrigation_schedules
ADD constraint plots_fk
FOREIGN KEY(plot_id)
references plots(plot_id)
on delete cascade;