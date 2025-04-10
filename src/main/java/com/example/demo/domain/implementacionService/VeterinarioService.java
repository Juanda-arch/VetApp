package com.example.demo.domain.implementacionService;



import com.example.demo.domain.DTO.VeterinarioDTO;
import com.example.demo.infrastructure.mapper.VeterinarioMapper;
import com.example.demo.domain.service.IVeterinarioService;
import com.example.demo.infrastructure.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeterinarioService implements IVeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final VeterinarioMapper veterinarioMapper;

    public VeterinarioDTO crearVeterinario(VeterinarioDTO dto) {
        var entity = veterinarioMapper.toEntity(dto);
        return veterinarioMapper.toDTO(veterinarioRepository.save(entity));
    }

    public List<VeterinarioDTO> obtenerVeterinarios() {
        return veterinarioRepository.findAll()
                .stream()
                .map(veterinarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VeterinarioDTO obtenerVeterinarioPorId(Long id) {
        return veterinarioRepository.findById(id)
                .map(veterinarioMapper::toDTO)
                .orElse(null);
    }

    public void eliminarVeterinario(Long id) {
        veterinarioRepository.deleteById(id);
    }

    public VeterinarioDTO actualizarVeterinario(Long id, VeterinarioDTO dto) {
        if (!veterinarioRepository.existsById(id)) return null;
        var entity = veterinarioMapper.toEntity(dto);
        entity.setId(id);
        return veterinarioMapper.toDTO(veterinarioRepository.save(entity));
    }
}

