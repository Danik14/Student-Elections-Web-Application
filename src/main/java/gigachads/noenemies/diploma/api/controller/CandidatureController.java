package gigachads.noenemies.diploma.api.controller;


import gigachads.noenemies.diploma.api.dto.CandidaturePlanUpdate;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.CandidaturePlan;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.CandidatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/candidature")
@RequiredArgsConstructor
public class CandidatureController {
    private final UserMapper userMapper;
    private final CandidatureService candidatureService;

    @PostMapping("/apply")
    public String applyForCandidature(Principal principal) {
        UserId studentId = UserId.of(principal.getName());
        System.out.println(studentId);

        candidatureService.applyForCandidature(studentId);
        return "Application was sent";
    }

    @PostMapping("/approve/{studentId}")
    public String approveCandidature(Principal principal, UserId studentId) {
        UserId officialId = UserId.of(principal.getName());

        candidatureService.approveCandidature(studentId, officialId);
        return "Application was sent";
    }

    @PatchMapping("/plan")
    public String updateCandidaturePlan(Principal principal,
                                        @RequestBody CandidaturePlanUpdate update) {
        UserId studentId = UserId.of(principal.getName());

        candidatureService.updateCandidaturePlan(update, studentId);
        return "Application was sent";
    }
}
