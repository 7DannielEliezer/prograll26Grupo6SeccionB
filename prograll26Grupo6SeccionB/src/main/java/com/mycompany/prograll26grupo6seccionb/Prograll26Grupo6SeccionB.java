package com.mycompany.prograll26grupo6seccionb;
import java.util.ArrayList;
import java.util.List;
import paquete.bd.reclutadora.Candidatos;
import paquete.bd.reclutadora.CandidatosJpaController;
import paquete.bd.reclutadora.Contrataciones;
import paquete.bd.reclutadora.ContratacionesJpaController;
import paquete.bd.reclutadora.Empresas;
import paquete.bd.reclutadora.EmpresasJpaController;
import paquete.bd.reclutadora.Evaluaciones;
import paquete.bd.reclutadora.EvaluacionesJpaController;
import paquete.bd.reclutadora.Postulaciones;
import paquete.bd.reclutadora.PostulacionesJpaController;
import paquete.bd.reclutadora.Vacantes;
import paquete.bd.reclutadora.VacantesJpaController;
import paquete.bd.reclutadora.HistorialCandidato;
import paquete.bd.reclutadora.Facturas;
/*-------------------------------*/
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Prograll26Grupo6SeccionB {

    public static void main(String[] args) {
        
            int opcion = 0;
               Scanner scanner = new Scanner(System.in);
                    opcion = scanner.nextInt();
            scanner.nextLine();
                    
            scanner.nextLine();
                   while (opcion != 1) {
switch (opcion) {



                       
                case 1:
                break;
                                default:
                    System.out.println("Opción no válida.");
                    break;


                    }

                   }
                   
/*
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_prograll26Grupo6SeccionB_jar_1.0-SNAPSHOTPU");
           EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n=== Menú de Reclutadora ===");
            System.out.println("1. Gestionar Empresas");
            System.out.println("2. Gestionar Candidatos");
            System.out.println("3. Gestionar Vacantes");
            System.out.println("4. Gestionar Evaluaciones");
            System.out.println("5. Gestionar Postulaciones");
            System.out.println("6. Gestionar Contrataciones");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción (1-7): ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    int opcionEmpresa = 0;

                    while (opcionEmpresa != 5) {

                        System.out.println("\n=== Gestión de Empresas ===");
                        System.out.println("1. Crear Empresa");
                        System.out.println("2. Actualizar Empresa");
                         System.out.println("3. Ver datos Empresa");
                        System.out.println("4. Borrar Empresa");
                         System.out.println("5. Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionEmpresa = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionEmpresa) {

                            case 1:
                                 crearEmpresa(em, scanner);
                                break;

                            case 2:
                                
                                actualizarEmpresa(em, scanner);
                                
                                break;

                            case 3:
                                verEmpresa(emf);
                                break;
                            case 4:
                                borrarEmpresa(em, scanner);
                                break;
                                
                            case 5:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }

                    break;

                case 2:
                    System.out.println("Gestión de Candidatos");
                                        int opcionCandidatos = 0;

                    while (opcionCandidatos != 5) {

                        System.out.println("\n=== Gestión de Candidatos ===");
                        System.out.println("1. Crear Candidatos");
                        System.out.println("2. Actualizar Candidatos");
                         System.out.println("3. Ver datos Candidatos");
                        System.out.println("4. Borrar Candidatos");
                         System.out.println("5. Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionCandidatos = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionCandidatos) {

                            case 1:
                                 crearCandidatos(em, scanner);
                                break;

                            case 2:
                                
                                actualizarCandidatos(em, scanner);
                                break;

                            case 3:
                                verCandidatos(emf);
                                break;
                            case 4:
                                borrarCandidatos(em, scanner);
                                break;
                                
                            case 5:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }

                    break;
                    
               

                case 3:
                    System.out.println("Gestión de Vacantes");
                                      
                                        int opcionVacantes = 0;

                    while (opcionVacantes != 5) {

                        System.out.println("\n=== Gestión de Vacantes ===");
                        System.out.println("1. Crear Vacantes");
                        System.out.println("2. Actualizar Vacantes");
                         System.out.println("3. Ver datos Vacantes");
                        System.out.println("4. Borrar Vacantes");
                         System.out.println("5. Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionVacantes = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionVacantes) {

                            case 1:
                                 crearVacantes(em, scanner);
                                break;                                
                            case 2:
                                actualizarVacantes(em, scanner);
                                break;

                            case 3:
                                verVacantes(emf);
                                break;
                            case 4:
                                borrarVacantes(em, scanner);
                                break;
                                
                            case 5:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }
                    
                    break;

                case 4:
                    System.out.println("Gestión de Evaluaciones");
                                                          
                                        int opcionEvaluaciones = 0;

                    while (opcionEvaluaciones != 5) {

                        System.out.println("\n=== Gestión de Evaluaciones===");
                        System.out.println("1. Crear Evaluaciones");
                        System.out.println("2. Actualizar Evaluaciones");
                         System.out.println("3. Ver datos Evaluaciones");
                        System.out.println("4. Borrar Evaluaciones");
                         System.out.println("5. Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionEvaluaciones = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionEvaluaciones) {

                            case 1:
                                 crearEvaluaciones(em, scanner);
                                break;                                
                            case 2:
                                actualizarEvaluaciones(em, scanner);
                                break;

                            case 3:
                                verEvaluaciones(emf);
                                break;
                            case 4:
                                borrarEvaluaciones(em, scanner);
                                break;
                                
                            case 5:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }
                    
                    
                    break;

                case 5:
                    System.out.println("Gestión de Postulaciones");                                                                                     
                    int opcionPostulaciones = 0;

                    while (opcionPostulaciones != 5) {

                        System.out.println("\n=== Gestión de Postulaciones===");
                        System.out.println("1. Crear Postulaciones");
                        System.out.println("2. Actualizar Postulaciones");
                         System.out.println("3. Ver datos Postulaciones");
                        System.out.println("4. Borrar Postulaciones");
                         System.out.println("5. Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionPostulaciones = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionPostulaciones) {

                            case 1:
                                 crearPostulaciones(em, scanner);
                                break;                                
                            case 2:
                                actualizarPostulaciones(em, scanner);
                                break;

                            case 3:
                                verPostulaciones(emf);
                                break;
                            case 4:
                                borrarPostulaciones(em, scanner);
                                break;
                                
                            case 5:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }
                    
                    break;

                case 6:
                    System.out.println("Gestión de Contrataciones");
                    int opcionContrataciones = 0;

                    while (opcionContrataciones != 5) {

                        System.out.println("\n=== Gestión de Contrataciones===");
                        System.out.println("1. Crear Contrataciones");
                        System.out.println("2. Actualizar Contrataciones");
                         System.out.println("3. Ver datos Contrataciones");
                        System.out.println("4. Borrar Contrataciones");
                         System.out.println("5. Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionContrataciones = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionContrataciones) {

                            case 1:
                                 crearContrataciones(em, scanner);
                                break;                                
                            case 2:
                                actualizarContrataciones(em, scanner);
                                break;

                            case 3:
                                verContrataciones(emf);
                                break;
                            case 4:
                                borrarContrataciones(em, scanner);
                                break;      
                            case 5:
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    }
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
        em.close();
        emf.close();
    }

    
    //////////////////////////////////////////////////////////////

public static void crearEmpresa(EntityManager em, Scanner scanner) {

    Empresas empresa = new Empresas();

    System.out.println("\n=== Crear Empresa ===");

    System.out.print("Ingrese el nombre de la empresa: ");
    String nombreEmpresa = scanner.nextLine();

    System.out.print("Ingrese el NIT: ");
    String nit = scanner.nextLine();

    System.out.print("Ingrese el teléfono: ");
    String telefono = scanner.nextLine();

    System.out.print("Ingrese el correo: ");
    String correo = scanner.nextLine();

    empresa.setNombreEmpresa(nombreEmpresa);
    empresa.setNit(nit);
    empresa.setTelefono(telefono);
    empresa.setCorreo(correo);

    try {
        em.getTransaction().begin();

        em.persist(empresa);

        em.getTransaction().commit();

        System.out.println("Empresa creada correctamente.");
        System.out.println("ID asignado: " + empresa.getEmpresaID());

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void actualizarEmpresa(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Actualizar Empresa ===");

    System.out.print("Ingrese el ID de la empresa que desea actualizar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la empresa por su ID
        Empresas empresa = em.find(Empresas.class, id);

        if (empresa == null) {
            System.out.println("[ERROR] No existe una empresa con el ID: " + id);
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Nombre: " + empresa.getNombreEmpresa());
        System.out.println("NIT: " + empresa.getNit());
        System.out.println("Teléfono: " + empresa.getTelefono());
        System.out.println("Correo: " + empresa.getCorreo());

        // Solicitar nuevos datos
        System.out.print("\nIngrese el nuevo nombre de la empresa: ");
        String nombreEmpresa = scanner.nextLine();

        System.out.print("Ingrese el nuevo NIT: ");
        String nit = scanner.nextLine();

        System.out.print("Ingrese el nuevo teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese el nuevo correo: ");
        String correo = scanner.nextLine();


        empresa.setNombreEmpresa(nombreEmpresa);
        empresa.setNit(nit);
        empresa.setTelefono(telefono);
        empresa.setCorreo(correo);

        em.getTransaction().begin();
        em.merge(empresa);

        em.getTransaction().commit();

        System.out.println("\nEmpresa actualizada correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void verEmpresa(EntityManagerFactory emf) {

    System.out.println("\n=== Lista de Empresas ===");

    List<Empresas> lstEmpresa = new ArrayList<>();

    EmpresasJpaController ec = new EmpresasJpaController(emf);

    try {

        lstEmpresa = ec.findEmpresasEntities();

        if (lstEmpresa.isEmpty()) {
            System.out.println("No hay empresas registradas.");
            return;
        }

        for (Empresas empresa : lstEmpresa) {

            System.out.println("ID: " + empresa.getEmpresaID());
            System.out.println("Nombre de la empresa: " + empresa.getNombreEmpresa());
            System.out.println("NIT: " + empresa.getNit());
            System.out.println("Teléfono: " + empresa.getTelefono());
            System.out.println("Correo: " + empresa.getCorreo());

            System.out.println("----------------------------");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());
    }
}
        
public static void borrarEmpresa(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Borrar Empresa ===");

    System.out.print("Ingrese el ID de la empresa que desea borrar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try{
        // Buscar la empresa por su ID
        Empresas empresa = em.find(Empresas.class, id);

        // Verificar si existe
        if (empresa == null) {
            System.out.println("[ERROR] No existe una empresa con el ID: " + id);
            return;
        }

        // Mostrar los datos antes de borrar
        System.out.println("\nEmpresa encontrada:");
        System.out.println("ID: " + empresa.getEmpresaID());
        System.out.println("Nombre: " + empresa.getNombreEmpresa());
        System.out.println("NIT: " + empresa.getNit());

        System.out.print("\nÂ¿EstÃ¡ seguro de que desea borrar esta empresa? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            em.getTransaction().begin();

            em.remove(empresa);

            em.getTransaction().commit();

            System.out.println("Empresa borrada correctamente.");

        } else {
            System.out.println("OperaciÃ³n cancelada.");
        }
     } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
    
    /////////////////////////////////////////

public static void crearCandidatos(EntityManager em, Scanner scanner) {

    Candidatos candidato = new Candidatos();

    System.out.println("\n=== Crear Candidato ===");

    System.out.print("Ingrese los nombres: ");
    String nombres = scanner.nextLine();

    System.out.print("Ingrese los apellidos: ");
    String apellidos = scanner.nextLine();

    System.out.print("Ingrese el DPI: ");
    String dpi = scanner.nextLine();

    System.out.print("Ingrese el correo: ");
    String correo = scanner.nextLine();

    System.out.print("Ingrese el teléfono: ");
    String telefono = scanner.nextLine();

    candidato.setNombres(nombres);
    candidato.setApellidos(apellidos);
    candidato.setDpi(dpi);
    candidato.setCorreo(correo);
    candidato.setTelefono(telefono);

    try {

        em.getTransaction().begin();

        em.persist(candidato);

        em.getTransaction().commit();

        System.out.println("\nCandidato creado correctamente.");
        System.out.println("ID asignado: " + candidato.getCandidatoID());

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void actualizarCandidatos(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Actualizar Candidato ===");

    System.out.print("Ingrese el ID del candidato que desea actualizar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar el candidato por su ID
        Candidatos candidato = em.find(Candidatos.class, id);

        if (candidato == null) {
            System.out.println("[ERROR] No existe un candidato con el ID: " + id);
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Nombres: " + candidato.getNombres());
        System.out.println("Apellidos: " + candidato.getApellidos());
        System.out.println("DPI: " + candidato.getDpi());
        System.out.println("Correo: " + candidato.getCorreo());
        System.out.println("Teléfono: " + candidato.getTelefono());

        // Solicitar nuevos datos
        System.out.print("\nIngrese los nuevos nombres: ");
        String nombres = scanner.nextLine();

        System.out.print("Ingrese los nuevos apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Ingrese el nuevo DPI: ");
        String dpi = scanner.nextLine();

        System.out.print("Ingrese el nuevo correo: ");
        String correo = scanner.nextLine();

        System.out.print("Ingrese el nuevo teléfono: ");
        String telefono = scanner.nextLine();

        // Actualizar los datos del candidato
        candidato.setNombres(nombres);
        candidato.setApellidos(apellidos);
        candidato.setDpi(dpi);
        candidato.setCorreo(correo);
        candidato.setTelefono(telefono);

        // Guardar los cambios
        em.getTransaction().begin();
        em.merge(candidato);

        em.getTransaction().commit();

        System.out.println("\nCandidato actualizado correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

public static void verCandidatos(EntityManagerFactory emf) {

    System.out.println("\n=== Lista de Candidatos ===");

    List<Candidatos> lstCandidatos = new ArrayList<>();

    CandidatosJpaController cc = new CandidatosJpaController(emf);

    try {

        lstCandidatos = cc.findCandidatosEntities();

        if (lstCandidatos.isEmpty()) {
            System.out.println("No hay candidatos registrados.");
            return;
        }

        for (Candidatos candidato : lstCandidatos) {

            System.out.println("ID: " + candidato.getCandidatoID());
            System.out.println("Nombres: " + candidato.getNombres());
            System.out.println("Apellidos: " + candidato.getApellidos());
            System.out.println("DPI: " + candidato.getDpi());
            System.out.println("Correo: " + candidato.getCorreo());
            System.out.println("Teléfono: " + candidato.getTelefono());

            System.out.println("----------------------------");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());
    }
}


public static void borrarCandidatos(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Borrar Candidato ===");

    System.out.print("Ingrese el ID del candidato que desea borrar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar el candidato por su ID
        Candidatos candidato = em.find(Candidatos.class, id);

        // Verificar si existe
        if (candidato == null) {
            System.out.println("[ERROR] No existe un candidato con el ID: " + id);
            return;
        }

        // Mostrar los datos antes de borrar
        System.out.println("\nCandidato encontrado:");
        System.out.println("ID: " + candidato.getCandidatoID());
        System.out.println("Nombres: " + candidato.getNombres());
        System.out.println("Apellidos: " + candidato.getApellidos());
        System.out.println("DPI: " + candidato.getDpi());
        System.out.println("Correo: " + candidato.getCorreo());
        System.out.println("Teléfono: " + candidato.getTelefono());

        System.out.print("\n¿Está seguro de que desea borrar este candidato? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            em.getTransaction().begin();

            em.remove(candidato);

            em.getTransaction().commit();

            System.out.println("Candidato borrado correctamente.");

        } else {
            System.out.println("Operación cancelada.");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


//////////////////////////////////////


public static void crearVacantes(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Crear Vacante ===");

    try {
        // Solicitar datos de la vacante
        System.out.print("Ingrese el título de la vacante: ");
        String tituloVacante = scanner.nextLine();

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese el sueldo ofrecido: ");
        BigDecimal sueldoOfrecido = new BigDecimal(scanner.nextLine());

        System.out.print("Ingrese el estado de la vacante: ");
        String estado = scanner.nextLine();

        System.out.print("Ingrese la fecha de publicación (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaPublicacion = formato.parse(fechaTexto);

        // Solicitar la empresa
        System.out.print("Ingrese el ID de la empresa: ");
        int empresaID = Integer.parseInt(scanner.nextLine());

        // Buscar la empresa
        Empresas empresa = em.find(Empresas.class, empresaID);

        if (empresa == null) {
            System.out.println("[ERROR] No existe una empresa con el ID: " + empresaID);
            return;
        }

        // Crear la vacante
        Vacantes vacante = new Vacantes();

        vacante.setTituloVacante(tituloVacante);
        vacante.setDescripcion(descripcion);
        vacante.setSueldoOfrecido(sueldoOfrecido);
        vacante.setEstado(estado);
        vacante.setFechaPublicacion(fechaPublicacion);
        vacante.setEmpresaID(empresa);

        // Iniciar transacción
        em.getTransaction().begin();

        em.persist(vacante);

        // Confirmar transacción
        em.getTransaction().commit();

        System.out.println("\nVacante creada correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void actualizarVacantes(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Actualizar Vacante ===");

    System.out.print("Ingrese el ID de la vacante que desea actualizar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la vacante por su ID
        Vacantes vacante = em.find(Vacantes.class, id);

        if (vacante == null) {
            System.out.println("[ERROR] No existe una vacante con el ID: " + id);
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Título: " + vacante.getTituloVacante());
        System.out.println("Descripción: " + vacante.getDescripcion());
        System.out.println("Sueldo ofrecido: " + vacante.getSueldoOfrecido());
        System.out.println("Estado: " + vacante.getEstado());
        System.out.println("Fecha de publicación: " + vacante.getFechaPublicacion());
        System.out.println("Empresa: " + vacante.getEmpresaID().getNombreEmpresa());

        // Solicitar nuevos datos
        System.out.print("\nIngrese el nuevo título de la vacante: ");
        String tituloVacante = scanner.nextLine();

        System.out.print("Ingrese la nueva descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese el nuevo sueldo ofrecido: ");
        BigDecimal sueldoOfrecido = new BigDecimal(scanner.nextLine());

        System.out.print("Ingrese el nuevo estado: ");
        String estado = scanner.nextLine();

        System.out.print("Ingrese la nueva fecha de publicación (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaPublicacion = formato.parse(fechaTexto);

        System.out.print("Ingrese el nuevo ID de la empresa: ");
        int empresaID = Integer.parseInt(scanner.nextLine());

        // Buscar la nueva empresa
        Empresas empresa = em.find(Empresas.class, empresaID);

        if (empresa == null) {
            System.out.println("[ERROR] No existe una empresa con el ID: " + empresaID);
            return;
        }

        // Actualizar los datos
        vacante.setTituloVacante(tituloVacante);
        vacante.setDescripcion(descripcion);
        vacante.setSueldoOfrecido(sueldoOfrecido);
        vacante.setEstado(estado);
        vacante.setFechaPublicacion(fechaPublicacion);
        vacante.setEmpresaID(empresa);

        // Iniciar transacción
        em.getTransaction().begin();

        em.merge(vacante);

        // Confirmar transacción
        em.getTransaction().commit();

        System.out.println("\nVacante actualizada correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

public static void verVacantes(EntityManagerFactory emf) {

    System.out.println("\n=== Lista de Vacantes ===");

    List<Vacantes> lstVacantes = new ArrayList<>();

    VacantesJpaController vc = new VacantesJpaController(emf);

    try {

        lstVacantes = vc.findVacantesEntities();

        if (lstVacantes.isEmpty()) {
            System.out.println("No hay vacantes registradas.");
            return;
        }

        for (Vacantes vacante : lstVacantes) {

            System.out.println("ID: " + vacante.getVacanteID());
            System.out.println("Título de la vacante: "
                    + vacante.getTituloVacante());
            System.out.println("Descripción: "
                    + vacante.getDescripcion());
            System.out.println("Sueldo ofrecido: "
                    + vacante.getSueldoOfrecido());
            System.out.println("Estado: "
                    + vacante.getEstado());
            System.out.println("Fecha de publicación: "
                    + vacante.getFechaPublicacion());

            if (vacante.getEmpresaID() != null) {
                System.out.println("Empresa: "
                        + vacante.getEmpresaID().getNombreEmpresa());
            }

            System.out.println("----------------------------");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());
    }
}


public static void borrarVacantes(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Borrar Vacante ===");

    System.out.print("Ingrese el ID de la vacante que desea borrar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la vacante por su ID
        Vacantes vacante = em.find(Vacantes.class, id);

        // Verificar si existe
        if (vacante == null) {
            System.out.println("[ERROR] No existe una vacante con el ID: " + id);
            return;
        }

        // Mostrar los datos antes de borrar
        System.out.println("\nVacante encontrada:");
        System.out.println("ID: " + vacante.getVacanteID());
        System.out.println("Título: " + vacante.getTituloVacante());
        System.out.println("Descripción: " + vacante.getDescripcion());
        System.out.println("Sueldo ofrecido: " + vacante.getSueldoOfrecido());
        System.out.println("Estado: " + vacante.getEstado());
        System.out.println("Fecha de publicación: " + vacante.getFechaPublicacion());

        if (vacante.getEmpresaID() != null) {
            System.out.println("Empresa: " + vacante.getEmpresaID().getNombreEmpresa());
        }

        System.out.print("\n¿Está seguro de que desea borrar esta vacante? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            // Iniciar transacción
            em.getTransaction().begin();

            // Eliminar la vacante
            em.remove(vacante);

            // Confirmar transacción
            em.getTransaction().commit();

            System.out.println("Vacante borrada correctamente.");

        } else {
            System.out.println("Operación cancelada.");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

///////////////////
public static void crearEvaluaciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Crear Evaluación ===");

    try {
        // Solicitar datos de la evaluación
        System.out.print("Ingrese el tipo de evaluación: ");
        String tipoEvaluacion = scanner.nextLine();

        System.out.print("Ingrese la fecha de evaluación (dd/MM/yyyy HH:mm): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaEvaluacion = formato.parse(fechaTexto);

        System.out.print("Ingrese el resultado: ");
        String resultado = scanner.nextLine();

        System.out.print("Ingrese los comentarios: ");
        String comentarios = scanner.nextLine();

    
        System.out.print("Ingrese el ID de la postulación: ");
        int postulacionID = Integer.parseInt(scanner.nextLine());


        Postulaciones postulacion = em.find(Postulaciones.class, postulacionID);

        if (postulacion == null) {
            System.out.println("[ERROR] No existe una postulación con el ID: " + postulacionID);
            return;
        }

        Evaluaciones evaluacion = new Evaluaciones();

        evaluacion.setTipoEvaluacion(tipoEvaluacion);
        evaluacion.setFechaEvaluacion(fechaEvaluacion);
        evaluacion.setResultado(resultado);
        evaluacion.setComentarios(comentarios);
        evaluacion.setPostulacionID(postulacion);

        em.getTransaction().begin();
        em.persist(evaluacion);
        em.getTransaction().commit();

        System.out.println("\nEvaluación creada correctamente.");

    } catch (Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void actualizarEvaluaciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Actualizar Evaluación ===");

    System.out.print("Ingrese el ID de la evaluación que desea actualizar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la evaluación por su ID
        Evaluaciones evaluacion = em.find(Evaluaciones.class, id);

        if (evaluacion == null) {
            System.out.println("[ERROR] No existe una evaluación con el ID: " + id);
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Tipo de evaluación: " + evaluacion.getTipoEvaluacion());
        System.out.println("Fecha de evaluación: " + evaluacion.getFechaEvaluacion());
        System.out.println("Resultado: " + evaluacion.getResultado());
        System.out.println("Comentarios: " + evaluacion.getComentarios());

        if (evaluacion.getPostulacionID() != null) {
            System.out.println("ID de postulación: "
                    + evaluacion.getPostulacionID().getPostulacionID());
        }

        // Solicitar nuevos datos
        System.out.print("\nIngrese el nuevo tipo de evaluación: ");
        String tipoEvaluacion = scanner.nextLine();

        System.out.print("Ingrese la nueva fecha de evaluación (dd/MM/yyyy HH:mm): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaEvaluacion = formato.parse(fechaTexto);

        System.out.print("Ingrese el nuevo resultado: ");
        String resultado = scanner.nextLine();

        System.out.print("Ingrese los nuevos comentarios: ");
        String comentarios = scanner.nextLine();

        System.out.print("Ingrese el nuevo ID de la postulación: ");
        int postulacionID = Integer.parseInt(scanner.nextLine());

        // Buscar la nueva postulación
        Postulaciones postulacion = em.find(Postulaciones.class, postulacionID);

        if (postulacion == null) {
            System.out.println("[ERROR] No existe una postulación con el ID: "
                    + postulacionID);
            return;
        }

        // Actualizar los datos
        evaluacion.setTipoEvaluacion(tipoEvaluacion);
        evaluacion.setFechaEvaluacion(fechaEvaluacion);
        evaluacion.setResultado(resultado);
        evaluacion.setComentarios(comentarios);
        evaluacion.setPostulacionID(postulacion);

        // Iniciar transacción
        em.getTransaction().begin();

        em.merge(evaluacion);

        // Confirmar transacción
        em.getTransaction().commit();

        System.out.println("\nEvaluación actualizada correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

public static void verEvaluaciones(EntityManagerFactory emf) {

    System.out.println("\n=== Lista de Evaluaciones ===");

    List<Evaluaciones> lstEvaluaciones = new ArrayList<>();

    EvaluacionesJpaController ec = new EvaluacionesJpaController(emf);

    try {

        lstEvaluaciones = ec.findEvaluacionesEntities();

        if (lstEvaluaciones.isEmpty()) {
            System.out.println("No hay evaluaciones registradas.");
            return;
        }

        for (Evaluaciones evaluacion : lstEvaluaciones) {

            System.out.println("ID: " + evaluacion.getEvaluacionID());
            System.out.println("Tipo de evaluación: "
                    + evaluacion.getTipoEvaluacion());
            System.out.println("Fecha de evaluación: "
                    + evaluacion.getFechaEvaluacion());
            System.out.println("Resultado: "
                    + evaluacion.getResultado());
            System.out.println("Comentarios: "
                    + evaluacion.getComentarios());

            if (evaluacion.getPostulacionID() != null) {
                System.out.println("ID de postulación: "
                        + evaluacion.getPostulacionID().getPostulacionID());
            }

            System.out.println("----------------------------");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());
    }
}


public static void borrarEvaluaciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Borrar Evaluación ===");

    System.out.print("Ingrese el ID de la evaluación que desea borrar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la evaluación por su ID
        Evaluaciones evaluacion = em.find(Evaluaciones.class, id);

        // Verificar si existe
        if (evaluacion == null) {
            System.out.println("[ERROR] No existe una evaluación con el ID: " + id);
            return;
        }

        // Mostrar los datos antes de borrar
        System.out.println("\nEvaluación encontrada:");
        System.out.println("ID: " + evaluacion.getEvaluacionID());
        System.out.println("Tipo de evaluación: " + evaluacion.getTipoEvaluacion());
        System.out.println("Fecha de evaluación: " + evaluacion.getFechaEvaluacion());
        System.out.println("Resultado: " + evaluacion.getResultado());
        System.out.println("Comentarios: " + evaluacion.getComentarios());

        if (evaluacion.getPostulacionID() != null) {
            System.out.println("ID de postulación: "
                    + evaluacion.getPostulacionID().getPostulacionID());
        }

        System.out.print("\n¿Está seguro de que desea borrar esta evaluación? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            // Iniciar transacción
            em.getTransaction().begin();

            // Eliminar la evaluación
            em.remove(evaluacion);

            // Confirmar transacción
            em.getTransaction().commit();

            System.out.println("Evaluación borrada correctamente.");

        } else {
            System.out.println("Operación cancelada.");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

////////////////////
public static void crearPostulaciones(EntityManager em, Scanner scanner) {

    Postulaciones postulacion = new Postulaciones();

    System.out.println("\n=== Crear Postulación ===");

    try {

        // Fecha de postulación
        System.out.print("Ingrese la fecha de postulación (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaPostulacion = formato.parse(fechaTexto);

        // Estado del candidato
        System.out.print("Ingrese el estado del candidato: ");
        String estadoCandidato = scanner.nextLine();

        // ID del candidato
        System.out.print("Ingrese el ID del candidato: ");
        int candidatoID = Integer.parseInt(scanner.nextLine());

        // Buscar candidato
        Candidatos candidato = em.find(Candidatos.class, candidatoID);

        if (candidato == null) {
            System.out.println("[ERROR] No existe un candidato con el ID: " + candidatoID);
            return;
        }

        // ID de la vacante
        System.out.print("Ingrese el ID de la vacante: ");
        int vacanteID = Integer.parseInt(scanner.nextLine());

        // Buscar vacante
        Vacantes vacante = em.find(Vacantes.class, vacanteID);

        if (vacante == null) {
            System.out.println("[ERROR] No existe una vacante con el ID: " + vacanteID);
            return;
        }

        // Asignar los datos
        postulacion.setFechaPostulacion(fechaPostulacion);
        postulacion.setEstadoCandidato(estadoCandidato);
        postulacion.setCandidatoID(candidato);
        postulacion.setVacantes(vacante);

        // Guardar en la base de datos
        em.getTransaction().begin();

        em.persist(postulacion);

        em.getTransaction().commit();

        System.out.println("\nPostulación creada correctamente.");
        System.out.println("ID asignado: " + postulacion.getPostulacionID());

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void actualizarPostulaciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Actualizar Postulación ===");

    System.out.print("Ingrese el ID de la postulación que desea actualizar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la postulación por su ID
        Postulaciones postulacion = em.find(Postulaciones.class, id);

        if (postulacion == null) {
            System.out.println("[ERROR] No existe una postulación con el ID: " + id);
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Fecha de postulación: " + postulacion.getFechaPostulacion());
        System.out.println("Estado del candidato: " + postulacion.getEstadoCandidato());

        if (postulacion.getCandidatoID() != null) {
            System.out.println("ID del candidato: "
                    + postulacion.getCandidatoID().getCandidatoID());
            System.out.println("Candidato: "
                    + postulacion.getCandidatoID().getNombres() + " "
                    + postulacion.getCandidatoID().getApellidos());
        }

        if (postulacion.getVacantes() != null) {
            System.out.println("ID de la vacante: "
                    + postulacion.getVacantes().getVacanteID());
            System.out.println("Vacante: "
                    + postulacion.getVacantes().getTituloVacante());
        }

        System.out.print("\nIngrese la nueva fecha de postulación (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaPostulacion = formato.parse(fechaTexto);

        System.out.print("Ingrese el nuevo estado del candidato: ");
        String estadoCandidato = scanner.nextLine();

        // Solicitar nuevo candidato
        System.out.print("Ingrese el nuevo ID del candidato: ");
        int candidatoID = Integer.parseInt(scanner.nextLine());

        Candidatos candidato = em.find(Candidatos.class, candidatoID);

        if (candidato == null) {
            System.out.println("[ERROR] No existe un candidato con el ID: " + candidatoID);
            return;
        }

        System.out.print("Ingrese el nuevo ID de la vacante: ");
        int vacanteID = Integer.parseInt(scanner.nextLine());

        Vacantes vacante = em.find(Vacantes.class, vacanteID);

        if (vacante == null) {
            System.out.println("[ERROR] No existe una vacante con el ID: " + vacanteID);
            return;
        }

        // Actualizar los datos
        postulacion.setFechaPostulacion(fechaPostulacion);
        postulacion.setEstadoCandidato(estadoCandidato);
        postulacion.setCandidatoID(candidato);
        postulacion.setVacantes(vacante);

        // Iniciar transacción
        em.getTransaction().begin();

        em.merge(postulacion);

        // Confirmar transacción
        em.getTransaction().commit();

        System.out.println("\nPostulación actualizada correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

public static void verPostulaciones(EntityManagerFactory emf) {

    System.out.println("\n=== Lista de Postulaciones ===");

    List<Postulaciones> lstPostulaciones = new ArrayList<>();

    PostulacionesJpaController pc = new PostulacionesJpaController(emf);

    try {

        lstPostulaciones = pc.findPostulacionesEntities();

        if (lstPostulaciones.isEmpty()) {
            System.out.println("No hay postulaciones registradas.");
            return;
        }

        for (Postulaciones postulacion : lstPostulaciones) {

            System.out.println("ID: " + postulacion.getPostulacionID());
            System.out.println("Fecha de postulación: "
                    + postulacion.getFechaPostulacion());
            System.out.println("Estado del candidato: "
                    + postulacion.getEstadoCandidato());

            if (postulacion.getCandidatoID() != null) {
                System.out.println("ID del candidato: "
                        + postulacion.getCandidatoID().getCandidatoID());
                System.out.println("Candidato: "
                        + postulacion.getCandidatoID().getNombres() + " "
                        + postulacion.getCandidatoID().getApellidos());
            }

            if (postulacion.getVacantes() != null) {
                System.out.println("ID de la vacante: "
                        + postulacion.getVacantes().getVacanteID());
                System.out.println("Vacante: "
                        + postulacion.getVacantes().getTituloVacante());
            }

            System.out.println("----------------------------");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());
    }
}


public static void borrarPostulaciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Borrar Postulación ===");

    System.out.print("Ingrese el ID de la postulación que desea borrar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la postulación por su ID
        Postulaciones postulacion = em.find(Postulaciones.class, id);

        // Verificar si existe
        if (postulacion == null) {
            System.out.println("[ERROR] No existe una postulación con el ID: " + id);
            return;
        }

        // Mostrar los datos antes de borrar
        System.out.println("\nPostulación encontrada:");
        System.out.println("ID: " + postulacion.getPostulacionID());
        System.out.println("Fecha de postulación: " + postulacion.getFechaPostulacion());
        System.out.println("Estado del candidato: " + postulacion.getEstadoCandidato());

        if (postulacion.getCandidatoID() != null) {
            System.out.println("Candidato: "
                    + postulacion.getCandidatoID().getNombres() + " "
                    + postulacion.getCandidatoID().getApellidos());
        }

        if (postulacion.getVacantes() != null) {
            System.out.println("Vacante: "
                    + postulacion.getVacantes().getTituloVacante());
        }

        System.out.print("¿Está seguro de que desea borrar esta postulación? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            // Iniciar transacción
            em.getTransaction().begin();

            // Eliminar la postulación
            em.remove(postulacion);

            // Confirmar transacción
            em.getTransaction().commit();

            System.out.println("Postulación borrada correctamente.");

        } else {
            System.out.println("Operación cancelada.");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

////////////////////////////////////////////////////////////
public static void crearContrataciones(EntityManager em, Scanner scanner) {

    Contrataciones contratacion = new Contrataciones();

    System.out.println("\n=== Crear Contratación ===");

    try {

        // Fecha de contratación
        System.out.print("Ingrese la fecha de contratación (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaContratacion = formato.parse(fechaTexto);

        // Sueldo acordado
        System.out.print("Ingrese el sueldo acordado: ");
        BigDecimal sueldoAcordado = new BigDecimal(scanner.nextLine());

        // Costo del servicio
        System.out.print("Ingrese el costo del servicio: ");
        BigDecimal costoServicio = new BigDecimal(scanner.nextLine());

        // ID de la postulación
        System.out.print("Ingrese el ID de la postulación: ");
        int postulacionID = Integer.parseInt(scanner.nextLine());

        // Buscar la postulación
        Postulaciones postulacion = em.find(Postulaciones.class, postulacionID);

        if (postulacion == null) {
            System.out.println("[ERROR] No existe una postulación con el ID: " + postulacionID);
            return;
        }

        // Asignar los datos
        contratacion.setFechaContratacion(fechaContratacion);
        contratacion.setSueldoAcordado(sueldoAcordado);
        contratacion.setCostoServicio(costoServicio);
        contratacion.setPostulaciones(postulacion);

        // Guardar en la base de datos
        em.getTransaction().begin();

        em.persist(contratacion);

        em.getTransaction().commit();

        System.out.println("Contratación creada correctamente.");
        System.out.println("ID asignado: " + contratacion.getContratacionID());

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

public static void verContrataciones(EntityManagerFactory emf) {

    System.out.println("\n=== Lista de Contrataciones ===");

    List<Contrataciones> lstContrataciones = new ArrayList<>();

    ContratacionesJpaController cc = new ContratacionesJpaController(emf);

    try {

        lstContrataciones = cc.findContratacionesEntities();

        if (lstContrataciones.isEmpty()) {
            System.out.println("No hay contrataciones registradas.");
            return;
        }

        for (Contrataciones contratacion : lstContrataciones) {

            System.out.println("ID: " + contratacion.getContratacionID());
            System.out.println("Fecha de contratación: "
                             + contratacion.getFechaContratacion());
            System.out.println("Sueldo acordado: "
                    + contratacion.getSueldoAcordado());
            System.out.println("Costo del servicio: "
                    + contratacion.getCostoServicio());

            if (contratacion.getPostulaciones() != null) {
                System.out.println("ID de postulación: "
                        + contratacion.getPostulaciones().getPostulacionID());
            }

            System.out.println("----------------------------");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());
    }
}

public static void actualizarContrataciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Actualizar Contratación ===");

    System.out.print("Ingrese el ID de la contratación que desea actualizar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la contratación por su ID
        Contrataciones contratacion = em.find(Contrataciones.class, id);

        if (contratacion == null) {
            System.out.println("[ERROR] No existe una contratación con el ID: " + id);
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Fecha de contratación: " + contratacion.getFechaContratacion());
        System.out.println("Sueldo acordado: " + contratacion.getSueldoAcordado());
        System.out.println("Costo del servicio: " + contratacion.getCostoServicio());

        if (contratacion.getPostulaciones() != null) {
            System.out.println("ID de postulación: "
                    + contratacion.getPostulaciones().getPostulacionID());
        }

        // Solicitar nuevos datos
        System.out.print("\nIngrese la nueva fecha de contratación (dd/MM/yyyy): ");
        String fechaTexto = scanner.nextLine();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaContratacion = formato.parse(fechaTexto);

        System.out.print("Ingrese el nuevo sueldo acordado: ");
        BigDecimal sueldoAcordado = new BigDecimal(scanner.nextLine());

        System.out.print("Ingrese el nuevo costo del servicio: ");
        BigDecimal costoServicio = new BigDecimal(scanner.nextLine());

        // Solicitar nueva postulación
        System.out.print("Ingrese el nuevo ID de la postulación: ");
        int postulacionID = Integer.parseInt(scanner.nextLine());

        // Buscar la postulación
        Postulaciones postulacion = em.find(Postulaciones.class, postulacionID);

        if (postulacion == null) {
            System.out.println("[ERROR] No existe una postulación con el ID: " + postulacionID);
            return;
        }

        // Actualizar los datos
        contratacion.setFechaContratacion(fechaContratacion);
        contratacion.setSueldoAcordado(sueldoAcordado);
        contratacion.setCostoServicio(costoServicio);
        contratacion.setPostulaciones(postulacion);

        // Iniciar transacción
        em.getTransaction().begin();

        em.merge(contratacion);

        // Confirmar transacción
        em.getTransaction().commit();

        System.out.println("\nContratación actualizada correctamente.");

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}

public static void borrarContrataciones(EntityManager em, Scanner scanner) {

    System.out.println("\n=== Borrar Contratación ===");

    System.out.print("Ingrese el ID de la contratación que desea borrar: ");
    int id = Integer.parseInt(scanner.nextLine());

    try {
        // Buscar la contratación por su ID
        Contrataciones contratacion = em.find(Contrataciones.class, id);

        // Verificar si existe
        if (contratacion == null) {
            System.out.println("[ERROR] No existe una contratación con el ID: " + id);
            return;
        }

        // Mostrar los datos antes de borrar
        System.out.println("\nContratación encontrada:");
        System.out.println("ID: " + contratacion.getContratacionID());
        System.out.println("Fecha de contratación: " + contratacion.getFechaContratacion());
        System.out.println("Sueldo acordado: " + contratacion.getSueldoAcordado());
        System.out.println("Costo del servicio: " + contratacion.getCostoServicio());

        if (contratacion.getPostulaciones() != null) {
            System.out.println("ID de postulación: "
                    + contratacion.getPostulaciones().getPostulacionID());
        }

        System.out.print("\n¿Está seguro de que desea borrar esta contratación? (S/N): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {

            // Iniciar transacción
            em.getTransaction().begin();

            // Eliminar la contratación
            em.remove(contratacion);

            // Confirmar transacción
            em.getTransaction().commit();

            System.out.println("Contratación borrada correctamente.");

        } else {
            System.out.println("Operación cancelada.");
        }

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        }
        */
    
}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
































/*                
Candidatos nuevoCandidatos = new Candidatos();
                
                System.out.print("Ingrese nombre: ");
                String nombre = scanner.nextLine();
                nuevoCandidatos.setNombres(nombre);
                        try {
            em.getTransaction().begin();
            em.persist(nuevoCandidatos);
            em.getTransaction().commit();
            } catch (Exception e) {
                    System.out.println("[ERROR] Prec " + e.getMessage());
                    em.getTransaction().rollback();
        } finally {
            em.close();
        }
                System.out.println("Usuario creado con éxito.");
                break;
                */