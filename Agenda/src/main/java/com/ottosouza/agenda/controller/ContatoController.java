package com.ottosouza.agenda.controller;

import java.util.List;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ottosouza.agenda.dto.ContatoDTO;
import com.ottosouza.agenda.entity.Contato;
import com.ottosouza.agenda.services.ContatoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

	@Autowired
	private ContatoService service;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Contato save(@RequestBody ContatoDTO dto) {
		return service.save(dto);

	}

	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@GetMapping
	public Page<Contato> list(@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina) {

		return service.findAllContacts(pagina, tamanhoPagina);
	}

	@GetMapping("all")
	public List<Contato> list() {
		return service.listAll();
	}

	@PatchMapping("{id}/favorito")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void favorito(@PathVariable Integer id) {
		service.atualizaFavorito(id);

	}

	@PutMapping("{id}/foto")
	public byte[] adicionarFoto(@PathVariable Integer id, @RequestParam("foto") Part foto) {
		return service.adicionarFotoContato(id, foto);
	}
}
