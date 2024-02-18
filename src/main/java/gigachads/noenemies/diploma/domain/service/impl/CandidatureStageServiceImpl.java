package gigachads.noenemies.diploma.domain.service.impl;

import gigachads.noenemies.diploma.domain.mapper.CandidatureMapper;
import gigachads.noenemies.diploma.domain.service.CandidatureStageService;
import gigachads.noenemies.diploma.storage.jpa.repository.CandidatureStageRepository;
import gigachads.noenemies.diploma.storage.jpa.repository.ElectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidatureStageServiceImpl implements CandidatureStageService {
    private final CandidatureStageRepository candidatureStageRepository;
    private final ElectionRepository electionRepository;
    private final CandidatureMapper candidatureMapper;
}
