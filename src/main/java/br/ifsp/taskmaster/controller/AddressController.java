package br.ifsp.taskmaster.controller;
            
            import org.modelmapper.ModelMapper;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.data.domain.Page;
            import org.springframework.data.domain.Pageable;
            import org.springframework.http.HttpStatus;
            import org.springframework.web.bind.annotation.RequestMapping;
            import org.springframework.web.bind.annotation.ResponseStatus;
            import org.springframework.web.bind.annotation.RestController;

import br.ifsp.taskmaster.dto.address.AddressRequestDTO;
import br.ifsp.taskmaster.dto.address.AddressResponseDTO;
import br.ifsp.taskmaster.exception.ResourceNotFoundException;
import br.ifsp.taskmaster.model.Address;
import br.ifsp.taskmaster.model.Contact;
import br.ifsp.taskmaster.repository.AddressRepository;
import br.ifsp.taskmaster.repository.ContactRepository;
import io.swagger.v3.oas.annotations.Operation;
            import jakarta.validation.Valid;
            
            import org.springframework.web.bind.annotation.GetMapping;
            import org.springframework.web.bind.annotation.PathVariable;
            import org.springframework.web.bind.annotation.PostMapping;
            import org.springframework.web.bind.annotation.RequestBody;
            
            @RestController
            @RequestMapping("/api/addresses")
            public class AddressController {
            
                @Autowired
                private ContactRepository contactRepository;
            
                @Autowired
                private AddressRepository addressRepository;
            
                @Autowired
                private ModelMapper modelMapper;
            
                @Operation(summary = "Buscar todos os endereços de um contato")
                @GetMapping("/contacts/{contactId}")
                public Page<AddressResponseDTO> getAddressesByContact(@PathVariable Long contactId, Pageable pageable) {
                    return addressRepository.findByContactId(contactId, pageable)
                            .map(address -> modelMapper.map(address, AddressResponseDTO.class));
                }
            
                @Operation(summary = "Criar novo endereço para um contato")
                @PostMapping("/contacts/{contactId}")
                @ResponseStatus(HttpStatus.CREATED)
                public AddressResponseDTO createAddress(@PathVariable Long contactId, @RequestBody @Valid AddressRequestDTO dto) {
                    Contact contact = contactRepository.findById(contactId)
                            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado: " + contactId));
            
                    Address address = modelMapper.map(dto, Address.class);
                    address.setContact(contact);
                    Address saved = addressRepository.save(address);
                    return modelMapper.map(saved, AddressResponseDTO.class);
                }
            }
            