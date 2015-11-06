/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entidade.Especialidades;
import entidade.Ubs;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbeans.EspecialidadesFacade;
import sessionbeans.UbsFacade;

/**
 *
 * @author german
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @EJB
    private UbsFacade ubsFacade;

    @EJB
    private EspecialidadesFacade especialidadesFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String tarefa = null;
        tarefa = request.getParameter("tarefa");

        //RequestDispatcher dispatcher = request.getRequestDispatcher("/view/index.jsp");
        //dispatcher.forward(request, response);
        if (tarefa==null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/index.jsp");
            dispatcher.forward(request, response);
        }

        if (tarefa.equals("importarCSV")) {
            Importar_CSV();
        }

        if (tarefa.equals("AcessarAcesso")) {
            
            List<Ubs> Unidades = ubsFacade.findAll();
            
            /*
            for(Ubs uni:Unidades){
                System.out.println(uni.getIdUBS());
                System.out.println(uni.getUnidade());
                System.out.println(uni.getLatitude());
                System.out.println(uni.getLongitude());
                System.out.println("_______________________");
            }
            */
            
            request.setAttribute("unidades", Unidades);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/acesso/acessoview.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void Importar_CSV() {

        URL stockURL = null;
        BufferedReader in = null;
        String Linha = null;

        Ubs unidade;
        Especialidades especialidade = null;
        List<Especialidades> especialidades = null;

        try {
            //Carregaga o arquivo CSV de uma URL
            //stockURL = new URL("http://dados.recife.pe.gov.br/storage/f/2013-10-14T17%3A07%3A28.389Z/ubs.csv");
            //in = new BufferedReader(new InputStreamReader(stockURL.openStream()));

            //Carregaga o arquivo CSV de uma pasta local
            //C:\\Users\\german\\Documents\\NetBeansProjects\\Sistema3A\\BD\\ubs.csv
            in = new BufferedReader(new InputStreamReader(new FileInputStream("/BD/ubs.csv"), "UTF-8"));

            boolean firstRows = true;
            int contadorCol;

            List<Ubs> Unidades = new ArrayList<Ubs>();

            //Verificar cada regitro do CSV
            while ((Linha = in.readLine()) != null) {
                //Só a partir do segundo registro será persistido
                if (!firstRows) {

                    contadorCol = 1;

                    unidade = new Ubs();

                    for (String item : Linha.split(";")) {

                        switch (contadorCol) {
                            //rpa
                            case 1:
                                unidade.setRpa(Integer.parseInt(item));
                                break;

                            //micro_regiao
                            case 2:
                                unidade.setMicroRegiao(Integer.parseInt(item));
                                break;

                            //cnes
                            case 3:
                                unidade.setCnes(Integer.parseInt(item));
                                break;

                            //unidade
                            case 4:
                                unidade.setUnidade(item);
                                break;

                            //endereco
                            case 5:
                                unidade.setEndereco(item);
                                break;

                            //bairro
                            case 6:
                                unidade.setBairro(item);
                                break;

                            //fone  
                            case 7:
                                unidade.setFone(item);
                                break;

                            //especialidades
                            case 8:

                                especialidades = new ArrayList<Especialidades>();

                                for (String NomeEspecialidade : item.split(",")) {
                                    especialidade = new Especialidades();
                                    especialidade.setNome(NomeEspecialidade.trim());
                                    if (NomeEspecialidade != "" || NomeEspecialidade != null) {
                                        especialidades.add(especialidade);
                                    }
                                }
                                break;

                            //latitude  
                            case 9:
                                item = item.replace(",", ".");
                                unidade.setLatitude(Double.parseDouble(item));
                                break;

                            //longitude 
                            case 10:
                                item = item.replace(",", ".");
                                unidade.setLongitude(Double.parseDouble(item));
                                break;
                        }
                        contadorCol++;
                    }
                    /*
                     System.out.println("rpa: " + unidade.getRpa());
                     System.out.println("micro região: " + unidade.getMicroRegiao());
                     System.out.println("cnes: " + unidade.getCnes());
                     System.out.println("unidade: " + unidade.getUnidade());
                     System.out.println("endereço: " + unidade.getEndereco());
                     System.out.println("bairro: " + unidade.getBairro());
                     System.out.println("fone: " + unidade.getFone());

                     String TodasEsp = "";
                     //for (Especialidades reg : unidade.getEspecialidadesCollection()) {
                     for (Especialidades reg : especialidades) {
                     TodasEsp += reg.getNome() + " ";
                     }
                     System.out.println("Especialidades: " + TodasEsp);

                     System.out.println("latitude: " + unidade.getLatitude());
                     System.out.println("longitude: " + unidade.getLongitude());
                     */
                    ubsFacade.create(unidade);

                    for (Especialidades E : especialidades) {
                        boolean achou = especialidadesFacade.VerificarNome(E.getNome());

                        if (!achou) {
                            especialidadesFacade.create(E);
                        }
                    }

                    unidade.setEspecialidadesCollection(especialidades);

                    Unidades.add(unidade);
                }
                firstRows = false;
            }

            //Solicitando todas as especialidades cadastradas
            List<Especialidades> AllEsp = especialidadesFacade.findAll();

            //Registrando a relação M-N Unidade-Especialidades
            for (Especialidades esp : AllEsp) {
                for (Ubs uni : Unidades) {
                    for (Especialidades UniEsp : uni.getEspecialidadesCollection()) {
                        if (UniEsp.getNome().equals(esp.getNome())) {
                            esp.getUbsCollection().add(uni);
                        }
                    }
                }
                especialidadesFacade.edit(esp);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
