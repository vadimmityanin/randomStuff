package my.app.validaha;

import my.app.validaha.val.service.Serv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/val")
@Validated
public class ValCtrl {

    @Autowired
    private Serv serv;
    @PostMapping
    public String go(@RequestBody  @Valid CoolDtoha coolDtoha) {
//        serv.val(coolDtoha);
//        System.err.println(coolDtoha.toString());

        return "from Ctrl";
    }
    @GetMapping
    public String goget(Pageable pageable) {
        System.err.println("here");
        return "get";
    }
}
