package services;

import daos.Usuario;

import java.util.Collection;
import java.util.HashMap;

public class UsuarioServiceImpl implements  UsuarioService {

    private HashMap<Object, Usuario> usuarioMap;

    public UsuarioServiceImpl() {
        usuarioMap = new HashMap<Object, Usuario>();
    }

    @Override
    public void addUsuario(Usuario usuario) {
        usuarioMap.put(usuario.getId(), usuario);
    }

    @Override
    public Collection<Usuario> getUsuario() {
        return usuarioMap.values();
    }

    @Override
    public Usuario getUsuario(Integer id) {
        return usuarioMap.get(id);
    }

    @Override
    public Usuario editUsuario(Usuario forEdit) {



        Usuario toEdit = usuarioMap.get(forEdit.getId());


        if (forEdit.getNombre() != null) {
            toEdit.setNombre(forEdit.getNombre());
        }
        if (forEdit.getId() != null) {
            toEdit.setId(forEdit.getId());
        }

        return toEdit;

    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioMap.remove(id);
    }
}
