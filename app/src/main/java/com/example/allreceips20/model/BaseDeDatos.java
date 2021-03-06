package com.example.allreceips20.model;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Database(entities = {Receta.class}, version = 2, exportSchema = false)
public abstract class BaseDeDatos extends RoomDatabase {
    private static volatile BaseDeDatos db;

    static Executor executor = Executors.newSingleThreadExecutor();

    public abstract RecetaDao obetenerRecetaDao();

    public static BaseDeDatos getInstance(final Context context){

        if (db == null){
            synchronized (BaseDeDatos.class){
                if (db == null){
                    db = Room.databaseBuilder(context, BaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);

                                    insertarDatosIniciales(getInstance(context).obetenerRecetaDao());
                                }

                                @Override
                                public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                                    super.onDestructiveMigration(db);

                                    insertarDatosIniciales(getInstance(context).obetenerRecetaDao());
                                }
                            })
                            .build();
                }
            }
        }
        return db;
    }

    private static void insertarDatosIniciales(RecetaDao dao) {
        executor.execute(() -> {
            dao.insertarReceta(new Receta("CREMA DE CALABAZA ASADA AL CURRY",
                    "Pela la calabaza y elimina las semillas y los filamentos. Corta la pulpa en dados y ponlos en la placa del horno, con los ajos lavados y sin pelar y las cebolletas limpias y lavadas. Riega con un hilo de aceite y ??salos 30 minutos en el horno precalentado a 200??. Retira y deja templar.\n Corta en aros la parte verde m??s tierna de las cebolletas y res??rvalos para decorar. Trocea el resto y pela los ajos. Tritura ambos con la calabaza hasta obtener un pur??. A??ade el caldo caliente y 1 cucharadita de curry, y cuece 10 minutos.\nTuesta las almendras sin aceite. Ret??ralas y ader??zalas con unas gotas de aceite y una pizca de sal. Sirve la crema caliente espolvoreada con las almendras, los aros de cebolleta reservados y el s??samo. ",
                    "file:///android_asset/cremita.jpg",1));

            dao.insertarReceta(new Receta("Lubina al vapor con patatas y mojo verde",
                    "Precalienta el horno a 200??. Lava las patatas y c??rtalas en gajos. Col??calas en una fuente refractaria y condimenta con una pizca de piment??n, cebolla y ajo en polvo. Roc??alas con un hilo de aceite y hornea 25 minutos.\nPela los ajos y trit??ralos, con el cilantro, la mitad del perejil, 3 cucharadas de aceite, 1 de vinagre y el comino hasta conseguir una pasta. Vierte el caldo en una cazuela y coloca el cestillo de cocci??n al vapor  encima.\nLava los filetes, s??calos y espolvor??alos con un poco de pimienta molida. Col??calos en el cestillo, a??ade unas ramas de perejil lavadas y cuece al vapor 8 minutos. S??rvelos junto con las patatas y el mojo verde.",
                    "file:///android_asset/lubinalucinante.jpg",1));

            dao.insertarReceta(new Receta("pavo con salsa de queso",
                    "Raspa las zanahorias y l??valas. Corta 2 en l??minas a lo largo y esc??ldalas en agua hirviendo. Trocea las otras 2, cu??celas 20 minutos en agua salada y esc??rrelas. Limpia la cebolleta, l??vala, p??cala y p??chala en 2 cucharadas de aceite.\nVierte el vino y el caldo, y deja reducir. Agrega la zanahoria y los quesos. Cuece hasta que la salsa espese, trit??rala y p??sala por el chino. Saltea las tiras de zanahoria con las espinacas y espolvorea con pimienta y tomillo.\nCorta el solomillo en rodajas gruesas, espolvor??alas con pimienta y ??salas a la plancha con unas gotas de aceite 3 minutos por cada lado. S??rvelo con la salsa de queso y las verduras.",
                    "file:///android_asset/pavo.jpg",1));

            dao.insertarReceta(new Receta("POTAJE R??PIDO DE GARBANZOS :D",
                    "Para preparar este potaje r??pido de garbanzos, empieza raspando las zanahorias con un pelaverduras. Luego, pela las patatas, l??valas y troc??alas. Limpia el puerro y las acelgas y troc??alos.\nPela y pica finitos los ajos. Mientras, calienta dos cucharadas de aceite de oliva en una cazuela.\nAgrega el ajo y, antes de que tome color, incorpora el puerro, las zanahorias y las patatas. Tapa y rehoga a fuego lento todo unos 10 minutos.\nA continuaci??n, a??ade el curry, la c??rcuma y el comino, y remueve con una esp??tula de madera. Cubre con el caldo caliente e incorpora ahora las acelgas. Cuece, a fuego medio, durante 15 minutos, siempre tapado.\nEcha en este momento los garbanzos bien enjuagados y escurridos, y prolonga la cocci??n de todo el potaje unos 10 minutos m??s.\nAntes de servir, espolvorea con una pizca de perejil lavado, seco y picado.",
                    "file:///android_asset/pota.jpg",1));

            dao.insertarReceta(new Receta("Empanadillas de atun y pisto",
                    "Lava el calabac??n, la berenjena y el pimiento y c??rtalos peque??os. Pela y pica la cebolla bien fina y ralla los tomates.\nEn una sart??n con 4 cucharadas de aceite sofr??e la cebolla y el pimiento 5 minutos. Echa el calabac??n y la berenjena y tapa para rehogar 10 minutos, removiendo de vez en cuando. A??ade el tomate, sala y sofr??e 10 minutos. Enfr??a.\nEscurre el at??n y a??ade el pisto. Coloca una porci??n de la mezcla en cada oblea y cierra presionando los extremos con un tenedor.\nBUENA IDEA: Fr??elas 2 minutos por cada cara o p??ntalas con huevo batido y horn??alas.",
                    "file:///android_asset/empanadas.jpg",1));
        });
    }

    @Dao
    public interface RecetaDao {
        @Insert
        void insertarReceta(Receta receta);

        @Update
        void editar(Receta receta);

        @Query("SELECT * FROM Receta WHERE lista = 0")
        LiveData<List<Receta>> obtenerRecetas();

        @Query("SELECT * FROM Receta WHERE lista = 1")
        LiveData<List<Receta>> obtenerRecetasIniciales();


        @Delete
        void eliminarReceta(Receta receta);

        @Query("SELECT * FROM Receta")
        LiveData<List<Receta>> obtenerTodasRecetas();

    }
}
