package com.ottosouza.agenda.services;

import java.awt.print.Pageable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ottosouza.agenda.dto.ContatoDTO;
import com.ottosouza.agenda.entity.Contato;
import com.ottosouza.agenda.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repo;

	public Contato save(ContatoDTO dto) {

		try {
			Contato contato = new Contato();
			contato.setNome(dto.getNome());
			contato.setEmail(dto.getEmail());
			contato.setFavorito(dto.getFavorito());

			return repo.save(contato);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public Page<Contato> findAllContacts(Integer pagina, Integer tamanhoPagina) {
		PageRequest pageR = PageRequest.of(pagina, tamanhoPagina);
		return repo.findAll(pageR);
	}

	public void atualizaFavorito(Integer id) {
		Optional<Contato> opContato = repo.findById(id);

		opContato.ifPresent(contato -> {
			boolean favorito = contato.getFavorito() == Boolean.TRUE;
			contato.setFavorito(!favorito);
			repo.save(contato);
		});

	}

	public byte[] adicionarFotoContato(Integer id, Part foto) {
		Optional<Contato> contato = repo.findById(id);
		return contato.map(c -> {
			try {

				// Representa o arquivo de bytes
				// representa a string de bytes.
				InputStream is = foto.getInputStream();

				// transformar o inputstream que vem do front em bytes
				byte[] bytes = new byte[(int) foto.getSize()];

				// classe de utilitaria para trabalhar com upload de byets
				// pegar o inputstream e adicionar no array de bytes
				// array de bytes preenchido
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				repo.save(c);
				is.close();
				return bytes;
			} catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}

	public List<Contato> listAll() {
		return repo.findAll();
	}

}
