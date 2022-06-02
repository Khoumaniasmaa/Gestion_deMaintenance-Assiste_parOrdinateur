package ma.emsi.gmao;

import ma.emsi.gmao.entities.Intervention;
import ma.emsi.gmao.repositories.InterventionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GmaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmaoApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(InterventionRepository interventionRepository){
        return args -> {interventionRepository.save(
                new Intervention(null, "vidange", new Date(), "cc", new Date(),"cc", "en cours"
                        ,"kiko","ss","dd"));;
            interventionRepository.save(
                    new Intervention(null, "Lavage", new Date(), "ff",  new Date(),"desc","zen"
                    ,"hh","hh","hh"));
            interventionRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
}
