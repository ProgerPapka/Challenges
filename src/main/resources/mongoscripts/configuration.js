rs.initiate()
cfg= {
           _id: "epamreplica",
           members: [
                      {
                       _id: 0,
                       host: "localhost:27001"
                      }
                    ]
         }

rs.reconfig(cfg)
rs.add("localhost:27002");
rs.addArb("localhost:27003");