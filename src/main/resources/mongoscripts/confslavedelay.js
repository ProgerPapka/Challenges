cfg = rs.conf();
cfg.members[1].priority=0;
cfg.members[1].slaveDelay=20;
rs.reconfig(cfg);