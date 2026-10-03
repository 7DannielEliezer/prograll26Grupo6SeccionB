package com.mycompany.prograll26grupo6seccionb;
import java.util.ArrayList;
import java.util.List;
import paquete.bd.reclutadora.Candidatos;
import paquete.bd.reclutadora.Contrataciones;
import paquete.bd.reclutadora.Empresas;
import paquete.bd.reclutadora.Evaluaciones;
import paquete.bd.reclutadora.Facturas;
import paquete.bd.reclutadora.HistorialCandidato;
import paquete.bd.reclutadora.Postulaciones;
import paquete.bd.reclutadora.Vacantes;
/*-------------------------------*/
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import paquete.bd.reclutadora.EmpresasJpaController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Prograll26Grupo6SeccionB {

    public static void main(String[] args) {

          EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_prograll26Grupo6SeccionB_jar_1.0-SNAPSHOTPU");
           EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 7) {
                     System.out.println("Danniel Eliezer Gómez Soto 6691-22-3902");
                     System.out.println("Luis eduardo Vasquez Garcia 6691-25-20380");
            
          
            
          

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
                                verCandidatos(em, scanner);
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
                                verVacantes(em, scanner);
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
                                verEvaluaciones(em, scanner);
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
                                verPostulaciones(em, scanner);
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
                                verContrataciones(em, scanner);
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

        // Actualizar los datos
        empresa.setNombreEmpresa(nombreEmpresa);
        empresa.setNit(nit);
        empresa.setTelefono(telefono);
        empresa.setCorreo(correo);

        em.getTransaction().begin();

        // Actualizar la entidad usando merge()
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


public static void actualizarCandidatos(EntityManager em, Scanner scanner){
}
public static void verCandidatos(EntityManager em, Scanner scanner){
}

public static void borrarCandidatos(EntityManager em, Scanner scanner){
}

//////////////////////////////////////
public static void crearVacantes(EntityManager em, Scanner scanner){
} 

public static void actualizarVacantes(EntityManager em, Scanner scanner){
}
public static void verVacantes(EntityManager em, Scanner scanner){
}

public static void borrarVacantes(EntityManager em, Scanner scanner){
}
///////////////////
public static void crearEvaluaciones(EntityManager em, Scanner scanner){
} 

public static void actualizarEvaluaciones(EntityManager em, Scanner scanner){
}
public static void verEvaluaciones(EntityManager em, Scanner scanner){
}

public static void borrarEvaluaciones(EntityManager em, Scanner scanner){
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


public static void actualizarPostulaciones(EntityManager em, Scanner scanner){
}
public static void verPostulaciones(EntityManager em, Scanner scanner){
}

public static void borrarPostulaciones(EntityManager em, Scanner scanner){
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

        System.out.println("\nContratación creada correctamente.");
        System.out.println("ID asignado: " + contratacion.getContratacionID());

    } catch (Exception e) {

        System.out.println("[ERROR] " + e.getMessage());

        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}


public static void actualizarContrataciones(EntityManager em, Scanner scanner){
}
public static void verContrataciones(EntityManager em, Scanner scanner){
}

public static void borrarContrataciones(EntityManager em, Scanner scanner){
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