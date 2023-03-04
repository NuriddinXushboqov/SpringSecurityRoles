package uz.najot.springsecurityroles.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.najot.springsecurityroles.message.ResMessage;
import uz.najot.springsecurityroles.model.AppUser;
import uz.najot.springsecurityroles.service.AppUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping()
    public ResMessage getAll(){
        return appUserService.getAll();
    }

    @GetMapping("/{id}")
    public ResMessage getById(@PathVariable int id){
        return appUserService.getById(id);
    }

    @PostMapping("/add")
    public ResMessage addUser(@RequestBody AppUser appUser){
        return appUserService.create(appUser);
    }

    @PutMapping("/update")
    public ResMessage update(@RequestBody AppUser appUser){
        return appUserService.edit(appUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResMessage delete(@PathVariable Integer id){
        return appUserService.remove(id);
    }
}
