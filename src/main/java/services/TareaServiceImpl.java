package services;

import daos.Tarea;

import java.util.Collection;
import java.util.HashMap;

public class TareaServiceImpl implements  TareaService {

    private HashMap<Object, Tarea> tareaMap;

    public TareaServiceImpl() {
        tareaMap = new HashMap<Object, Tarea>();
    }

    @Override
    public void addTarea(Tarea tarea) {
        tareaMap.put(tarea.getId(), tarea);
    }

    @Override
    public Collection<Tarea> getTarea() {
        return tareaMap.values();
    }

    @Override
    public Tarea getTarea(Integer id) {
        return tareaMap.get(id);
    }

    @Override
    public Tarea editTarea(Tarea forEdit) {

        Tarea toEdit = tareaMap.get(forEdit.getId());


        if (forEdit.getNombre() != null) {
            toEdit.setNombre(forEdit.getNombre());
        }
        if (forEdit.getDescripcion() != null){
            toEdit.setDescripcion(forEdit.getDescripcion());
        }
        if (forEdit.getUsuarioCreacion() != null){
            toEdit.setUsuarioCreacion(forEdit.getUsuarioCreacion());
        }
        if (forEdit.getFechaCreacion() != null){
            toEdit.setFechaCreacion(forEdit.getFechaCreacion());
        }
        if (forEdit.getId() != null) {
            toEdit.setId(forEdit.getId());
        }

        return toEdit;

    }

    @Override
    public void deleteTarea(Integer id) {
        tareaMap.remove(id);
    }
}
