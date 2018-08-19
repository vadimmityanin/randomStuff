package my.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Populator {
    @Autowired
    private TTTRepository repository;

    public void go(){
//        for (int i = 0; i < 100; i++) {
//            repository.save(new TTTZ(null, new Random().toString()));
//        }
        repository.save(new TTTZ(801L,"valera"));
    }
}
