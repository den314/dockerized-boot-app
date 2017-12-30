package pl.desz.sample;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class SampleAppBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleAppBootApplication.class, args);
    }

    @Autowired
    private TaskRepository tr;

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            tr.save(new Task("do some coding"));
            tr.save(new Task("go running"));
            tr.save(new Task("buy flowers for your wife"));
        };
    }
}

@Data
@Entity
@NoArgsConstructor
class Task {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean done = false;

    Task(String name) {
        this.name = name;
    }
}

@Repository
interface TaskRepository extends CrudRepository<Task, Long> {
}

@RestController
@RequestMapping("/task")
class TaskController {

    @Autowired
    private TaskRepository tr;

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return tr.findOne(id);
    }

    @PostMapping
    public ResponseEntity<?> addNewTask(@RequestBody Task task) {
        tr.save(task);
        return ResponseEntity.ok().build(); //enough for sample
    }
}
