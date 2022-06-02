package ma.emsi.gmao.web;

import lombok.AllArgsConstructor;
import ma.emsi.gmao.entities.Intervention;
import ma.emsi.gmao.repositories.InterventionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class InterventionController {
    private InterventionRepository interventionRepository;
    @GetMapping (path="/index") /*nom de pages*/
   public String intervention(Model model ,
                          @RequestParam(name ="page",defaultValue = "0") int page,
                          @RequestParam(name ="size",defaultValue = "5")int size,
                          @RequestParam(name ="keyword",defaultValue = "") String keyword
    ){
        Page<Intervention> pageintervention=interventionRepository.findByNomContains((keyword), PageRequest.of(page,size));
        model.addAttribute( "listintervention",pageintervention.getContent());
        model.addAttribute("pages",new int[pageintervention.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
     return "Intervention";
   }
   @GetMapping("/delete")
   public String delete(Long id,String keyword,int page){
       interventionRepository.deleteById(id);
       return "redirect:/index?page"+page+"&keyword"+keyword;
   }
   @GetMapping("/")
    public String home(){
       return "redirect:/accueil";
   }
   @GetMapping("/Intervention")
   @ResponseBody
   public List<Intervention> listintervention(){
       return interventionRepository.findAll();
   }
@GetMapping("/formIntervention")
   public String formIntervention(Model model){
       model.addAttribute("intervention",new Intervention());
       return "formIntervention";
}
@PostMapping(path = "/save")
public  String save (Model model, @Valid Intervention intervention, BindingResult bindingResult,
                     @RequestParam(defaultValue="")String keyword, @RequestParam(defaultValue="0") int page){
       if (bindingResult.hasErrors()) return "formIntervention";
      interventionRepository.save(intervention);
       return "redirect:/index?page="+page+"&keyword="+keyword;
}
    @GetMapping(path = "/editIntervention")
    public  String editIntervention(Model model,long id , String keyword, int page){
       Intervention intervention=interventionRepository.findById(id).orElse(null);
        if (intervention==null)throw  new RuntimeException("Intervention introuvable");
        model.addAttribute("intervention",intervention);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editIntervention";
    }
    @GetMapping(path= "/det")
    public String det(Model model,long id, String keyword, int page)
    {
      Intervention intervention=interventionRepository.findById(id).orElse(null);
        if (intervention==null)throw  new RuntimeException("Intervention introuvable");
        model.addAttribute("intervention",intervention);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "detail";
    }
   @GetMapping("/Dashboard")
    public String Dashboard(){
        return "Dashboard";
    }
    @GetMapping("/Login")
    public String Login(){
       return "Login";
    }
    @GetMapping("/accueil")
    public String accueil(){
        return "accueil";
    }
    @GetMapping("/acc")
    public String acc(){
        return "accu";
    }
    @GetMapping("/Profil")
    public String Profil(){
        return "Profil";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/forgot-password")
    public String Forgot_Password(){
        return "forgot-password";
    }



    }

