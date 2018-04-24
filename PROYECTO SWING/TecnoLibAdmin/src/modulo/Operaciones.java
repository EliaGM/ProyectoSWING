/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo;

import entidades.Idioma;
import entidades.Producto;
import entidades.Seccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Operaciones {
  
    public Operaciones() {
    }
    public int addProducto(Producto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        int devuelve = 0;
        try{
            em.persist(p);
            em.getTransaction().commit();
            devuelve = 1;
            System.out.println("Se guardo el producto NUEVO");
        }catch(Exception ex){
            System.out.println("ISBN YA EXISTE");
            em.getTransaction().rollback();
        }finally{
            em.clear();
            em.close();
            return devuelve;
        }
    }
    public List<Seccion> getSecciones(){
        List<Seccion> secciones=null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Query query = em.createNamedQuery("getSecciones");
            secciones = (List<Seccion>)query.getResultList();
            em.clear();
            em.close();
            
            System.out.println("getSecciones");
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            return secciones;
        }
    }
    public List<Idioma> getIdiomas(){
        List<Idioma> idiomas=null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Query query = em.createNamedQuery("getIdiomas");
            idiomas = (List<Idioma>)query.getResultList();
            em.clear();
            em.close();
            
            System.out.println("getIdiomas");
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            return idiomas;
        }
    }
    public Seccion getSeccion(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Seccion seccion = null;
        try{
            seccion = em.find(Seccion.class, id);
            em.clear();
            em.close();
            
            System.out.println("getSeccion");
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            return seccion;
        }
    }
    public Idioma getIdioma(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Idioma idioma = null;
        try{
            idioma = em.find(Idioma.class, id);
            em.clear();
            em.close();
            
            System.out.println("getIdioma");
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            return idioma;
        }
    }
    public Producto getProducto(int idproducto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Producto p = null;
        try{
            p = em.find(Producto.class, idproducto);
            em.clear();
            em.close();
            
            System.out.println("getProducto");
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            return p;
        }
    }
    public Producto getProducto(String isbn){
        Producto p = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Query query = em.createNamedQuery("getProducto");
            query.setParameter("isbn", isbn);
            p = (Producto)query.getSingleResult();
            em.clear();
            em.close();
            
            System.out.println("getProducto");
        }catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            return p;
        }
    }
    
    public void modificar(Producto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Se modifico correctamente");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        }finally{
            em.clear();
            em.close();
        }
    }
    
    public void eliminar(int idproducto){
        //hacemos modificacion del campo descatalogado para guardar la consistencia en la BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Producto p = em.find(Producto.class, idproducto);
            if(p!=null){
                em.remove(p);
            }
            em.getTransaction().commit();
            System.out.println("Se elimino correctamente");
        }catch(Exception ex){
            System.out.println("SE HA PRODUCIDO UN ERROR");
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        }finally{
            em.clear();
            em.close();
        }
    }
    public void descatalogar(int idproducto){
        //hacemos modificacion del campo descatalogado para guardar la consistencia en la BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecnoLibPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Producto p = em.find(Producto.class, idproducto);
            p.setDescatalogado(1);
            if(p!=null){
                em.merge(p);
            }
            em.getTransaction().commit();
            System.out.println("Se descatalogar correctamente");
        }catch(Exception ex){
            System.out.println("SE HA PRODUCIDO UN ERROR descatalogar");
            System.out.println(ex.getMessage());
            em.getTransaction().rollback();
        }finally{
            em.clear();
            em.close();
        }
    }
    
}
