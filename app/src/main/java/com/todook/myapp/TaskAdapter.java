package com.todook.myapp;

import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.todook.myapp.db.DbHelper;
import com.todook.myapp.modelo.Task;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder >{

    private final DbHelper dbHelper; // Agrega una instancia de DbHelper en el adaptador

    //construye la lista de tareas
    ArrayList<Task> tasks;
    Activity activity;
    public TaskAdapter(ArrayList<Task> tasks, Activity activity){
        this.tasks = tasks;
        this.activity = activity;
        this.dbHelper = new DbHelper(activity); // Inicializa la instancia de DbHelper en el constructor

    }

    //inflar el layout y lo pasara al viewholder para que el obtenga los views
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalle_task,parent, false );
        return new TaskViewHolder(v);
    }
    //asocia cada elemento de la lista a cada view
    @Override
    public void onBindViewHolder(TaskViewHolder taskViewHolder, int position) {
        final Task task = tasks.get(position);
        int tipo = task.getType();

        switch (tipo) {
            case 1:
                taskViewHolder.icon_type_uno.setImageResource(R.drawable.baseline_circle_red_24);
                break;
            case 2:
                taskViewHolder.icon_type_uno.setImageResource(R.drawable.baseline_circle_yellow_24);
                break;
            case 3:
                taskViewHolder.icon_type_uno.setImageResource(R.drawable.baseline_circle_green_24);
                break;
            default:
                break;
        }

        taskViewHolder.date1.setText(task.getTaskdate());
        taskViewHolder.time1.setText(task.getTimedate());
        taskViewHolder.title1.setText(task.getTaskname());

        taskViewHolder.cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                // Inflar el menú de contexto desde el archivo XML
                Activity activity = (Activity) view.getContext();
                activity.getMenuInflater().inflate(R.menu.menu_contexto, contextMenu);

                // Agregar el listener para el menú de contexto
                contextMenu.findItem(R.id.menu_delete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Obtener la tarea seleccionada
                        int clickedPosition = taskViewHolder.getAdapterPosition();
                        Task selectedTask = tasks.get(clickedPosition);
                        // Eliminar la tarea de la base de datos utilizando su ID
                        dbHelper.eliminarTask(selectedTask.getId());
                        // Eliminar la tarea del RecyclerView
                        tasks.remove(clickedPosition);
                        Toast.makeText(activity, "Tarea Eliminada", Toast.LENGTH_SHORT).show();

                        notifyItemRemoved(clickedPosition);// Utiliza notifyItemRemoved en lugar de notifyDataSetChanged

                        return true;
                    }
                });

                contextMenu.findItem(R.id.menu_edit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int clickedPosition = taskViewHolder.getAdapterPosition();
                        Task selectedTask = tasks.get(clickedPosition);


                        // Crear un Intent para la actividad de edición
                        Intent intent = new Intent(activity, TaskEditActivity.class);


                        // Poner datos extras en el Intent
                        intent.putExtra("taskId", selectedTask.getId());
                        intent.putExtra("taskName", selectedTask.getTaskname());
                        intent.putExtra("taskDate", selectedTask.getTaskdate());
                        intent.putExtra("timeDate", selectedTask.getTimedate());
                        intent.putExtra("taskType", selectedTask.getType());


                        // Iniciar la actividad de edición
                        activity.startActivity(intent);


                        return true;
                    }
                });

            }


        });




    }

    @Override
   public int getItemCount() {//cantidad de elementos de la lista
        return tasks.size();
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private final TextView date1;
        private final TextView time1;
        private final TextView title1;
        private final ImageView icon_type_uno;
        private CardView cardView;

        public TaskViewHolder(View itemView){
            super(itemView);
            date1 = itemView.findViewById(R.id.date1);
            time1 = itemView.findViewById(R.id.time1);
            title1 = itemView.findViewById(R.id.title1);
            icon_type_uno = itemView.findViewById(R.id.icon_type_uno);
            cardView=itemView.findViewById(R.id.cvTask);
            // Agrega el listener de clic largo en la vista de la tarjeta
            cardView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            // Infla el menú de contexto desde el archivo XML
            Activity activity = (Activity) view.getContext();
            activity.getMenuInflater().inflate(R.menu.menu_contexto, contextMenu);
        }




    }

    public void actualizarTareas(ArrayList<Task> nuevasTareas) {
        tasks = nuevasTareas;
        notifyDataSetChanged();
    }

}