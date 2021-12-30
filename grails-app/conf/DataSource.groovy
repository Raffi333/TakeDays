import org.hibernate.dialect.MySQL5InnoDBDialect

dataSource {
	dialect = MySQL5InnoDBDialect
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = "net.sf.ehcache.hibernate.EhCacheProvider"
	singleSession = true // configure OSIV singleSession mode
	flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
		    dataSource {
			      dbCreate = "update" // one of 'create', 'create-drop','update'
			      url = "jdbc:mysql://localhost:3306/TekDays_development"
		    }
	  }
	  test {
		    dataSource {
			      dbCreate = "update"
			      url = "jdbc:mysql://localhost:3306/TekDays_test"
		    }
	  }
	  production {
		    dataSource {
			      dbCreate = "update"
			      url = "jdbc:mysql://localhost:3306/TekDays_production"
		    }
	  }
}