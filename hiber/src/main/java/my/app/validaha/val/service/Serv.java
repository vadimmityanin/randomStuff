package my.app.validaha.val.service;

import my.app.validaha.CoolDtoha;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface Serv {
    void val(@Valid CoolDtoha dtoha);
}
