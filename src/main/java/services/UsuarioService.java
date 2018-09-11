package services;

import daos.Usuario;

import java.util.Collection;

public interface UsuarioService {

    public void addUsuario(Usuario usuario);

    public Collection<Usuario> getUsuario();

    public Usuario getUsuario(Integer id);

    public Usuario editUsuario(Usuario forEdit);

    public void deleteUsuario(Integer id);
}
