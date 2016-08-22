package com.fatec.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatec.model.Aluno;
import com.fatec.repository.AlunoRepository;

@Controller
@RequestMapping("/alunos")
public class FatecControler {
	private static final String CadastroView="CadastroAluno";
	@Autowired
	private AlunoRepository repositorioAluno;
	
	@RequestMapping("/novo")
	public String novo(Model model) {
		model.addAttribute(new Aluno());
		return CadastroView;
	}
	
	@RequestMapping(value="/cadastrodocumento/{id}")
	public String adicionadocumento(@PathVariable Long id, RedirectAttributes attributes) {
		attributes.addFlashAttribute("alunoid", id);
		return "redirect:/documentos/novo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Aluno aluno, Errors erro, RedirectAttributes attributes) {
		if (erro.hasErrors()) {
			return CadastroView;
		}
		repositorioAluno.save(aluno);
		attributes.addAttribute("mensagem","O titulo salvo com sucesso!");
		return "redirect:/alunos/novo";
	}
	
	@RequestMapping(value="{id}")
	public ModelAndView edicao(@PathVariable Long id, RedirectAttributes attributes,Model model) {
		Aluno aluno = this.repositorioAluno.findOne(id);
		ModelAndView mv = new ModelAndView(CadastroView);
		mv.addObject(aluno);
		return mv;	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		this.repositorioAluno.delete(id);
		attributes.addFlashAttribute("mensagem","titulo excluido com sucesso!");
		return "redirect:/alunos";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Aluno> todosAlunos = this.repositorioAluno.findAll();
		ModelAndView mv = new ModelAndView("PesquisaAluno");
		mv.addObject("alunos",todosAlunos);
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.GET)
	public String pesquisar(Model model) {
		List<Aluno> alunos = this.repositorioAluno.findAll();
		model.addAttribute("alunos", alunos);
		return "PesquisaAluno";
	}
	
	
}
