package com.loader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cache.Cache;
import com.model.Users;
import com.repository.UsersRepository;

@Component
public class Loader {
	private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Autowired
    UsersRepository usersRepository;
    
    @Autowired
    Cache cache;

    @PostConstruct
    public List<Users> loadAndCacheAllUsers() {
    	if (cache.get("someKey") == null ) {
    		log.info("*_* loading dummy hardcoded users into DB");
    		List<Users> usersList = getList();
    		usersRepository.saveAll(usersList);
    		log.info("*_* getting all users from DB and storing in cache");
    		List<Users> allUsers = usersRepository.findAll();
    		cache.add("someKey", allUsers, 40000);
		} 
    	log.info("*_* loading users from cache");
    	return (List<Users>) cache.get("someKey");
    }

    public List<Users> getList() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(new Users("Srikanth", "Java",123L));
        usersList.add(new Users("Dannarapu", "UI",13L));
        usersList.add(new Users("Raja", "Filenet",23L));
        usersList.add(new Users("Kaja", "DB2",100L));
        usersList.add(new Users("dodo", "Dojo",10L));
        usersList.add(new Users("samm", "Python",16L));
        return usersList;
    }
}