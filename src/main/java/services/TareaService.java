package services;

import daos.Tarea;

import java.util.Collection;


public interface TareaService{

    public void addTarea(Tarea tarea);

    public Collection<Tarea> getTarea();

    public Tarea getTarea(Integer id);

    public Tarea editTarea(Tarea forEdit);

    public void deleteTarea(Integer id);
}
