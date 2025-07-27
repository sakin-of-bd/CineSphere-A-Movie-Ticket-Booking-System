package app.all_files;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.time.LocalTime;
import java.util.Date;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Admin_Dashboard_Controller implements Initializable {

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_date;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData,String> addMovies_col_movieTitle;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<moviesData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<moviesData,String> availableMovies_col_genre;

    @FXML
    private TableColumn<moviesData,String> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<moviesData,String> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_comfort_price;

    @FXML
    private Spinner<Integer> availableMovies_comfort_quantity;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Label availableMovies_saver_price;

    @FXML
    private Spinner<Integer> availableMovies_saver_quantity;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private TableView<moviesData> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Label availableMovies_vip_price;

    @FXML
    private Spinner<Integer> availableMovies_vip_quantity;

    @FXML
    private Label customers_boughtDate;

    @FXML
    private Label customers_boughtTime;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableView<customerData> customer_tableView;

    @FXML
    private TableColumn<customerData,String> customers_col_BoughtTime;

    @FXML
    private TableColumn<customerData,String> customers_col_boughtDate;

    @FXML
    private TableColumn<customerData,String> customers_col_movieTitle;

    @FXML
    private TableColumn<customerData,String> customers_col_ticketNumber;

    @FXML
    private TableColumn<customerData,String> customers_col_totalPayment;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private Label customers_ticket;

    @FXML
    private Label customers_totalPayment;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnToday;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private Label editScreening_MovieTitle;

    @FXML
    private TableColumn<moviesData,String> editScreening_col_current;

    @FXML
    private TableColumn<moviesData,String> editScreening_col_duration;

    @FXML
    private TableColumn<moviesData,String> editScreening_col_genre;

    @FXML
    private TableColumn<moviesData,String> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_clearBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private Button editScreening_updateBtn;

    @FXML
    private TableView<moviesData> addScreening_tableView;

    @FXML
    private Button signout;

    private Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    //****************************************************************************************************//
    //*************************************Admin Dashboard Part******************************************//


    private double totalIncome;
    public void totalIncomeToday(){
        Date date=new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql="SELECT SUM(total) FROM customer WHERE date = '"+
                String.valueOf(sqlDate)+"'";

        connect=database.connectDB();

        try{

            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            if(result.next()){
                totalIncome=result.getDouble("SUM(total)");
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }

    public void displayTotalIncomeToday(){
        totalIncomeToday();
        dashboard_totalEarnToday.setText(String.valueOf(totalIncome));
    }

    private int soldTicket;
    public void countTicket(){
        String sql="SELECT count(id) FROM customer";

        connect=database.connectDB();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void displayTotalSoldTicket(){
        countTicket();
        dashboard_totalSoldTicket.setText((String.valueOf(getData.ticket)));
    }


    private int totalMovies;
    public void totalAvailableMovies(){
        String sql="SELECT count(id) FROM movie WHERE current = 'Showing'";

        connect=database.connectDB();

        try{

            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            if(result.next()){
                totalMovies=result.getInt("count(id)");
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }

    public void displayTotalAvailableovies(){
        totalAvailableMovies();
        dashboard_availableMovies.setText(String.valueOf(totalMovies));
    }




    //****************************************************************************************************//
    //*************************************Admin Dashboard Part Finish******************************************//



    //****************************************************************************************************//
    //*************************************Customer Part******************************************//


     public ObservableList<customerData>customerList(){
         ObservableList<customerData>customerL=FXCollections.observableArrayList();

         String sql="SELECT * FROM customer";

         connect=database.connectDB();
         try{
             customerData cD;
             prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();

             while(result.next()){
                 cD=new customerData(result.getInt("id"),
                         result.getString("type"),
                         result.getString("movieTitle"),
                         result.getInt("quantity"),
                         result.getDouble("total"),
                         result.getDate("date"),
                         result.getTime("time"));

                 customerL.add(cD);
             }

         }catch(Exception e){
             e.printStackTrace();
         }
         return customerL;
     }

     private ObservableList<customerData>custList;
     public void showCustomerList(){
         custList=customerList();

         customers_col_ticketNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
         customers_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         customers_col_boughtDate.setCellValueFactory(new PropertyValueFactory<>("date"));
         customers_col_BoughtTime.setCellValueFactory(new PropertyValueFactory<>("time"));

         customer_tableView.setItems(custList);
     }

     public void selectCustmerList(){
         customerData custD=customer_tableView.getSelectionModel().getSelectedItem();
                 int num=customer_tableView.getSelectionModel().getSelectedIndex();

                 if(num-1<-1){
                     return;
                 }
                 customers_ticket.setText(String.valueOf(custD.getId()));
                 customers_movieTitle.setText(String.valueOf(custD.getTitle()));
                 customers_boughtDate.setText(String.valueOf(custD.getDate()));
                 customers_boughtTime.setText(String.valueOf(custD.getTime()));

     }

     public void clearCustomer(){
         customers_ticket.setText("");
         customers_movieTitle.setText("");
         customers_boughtDate.setText("");
         customers_boughtTime.setText("");

     }

     public void deleteCustomer(){
         String sql="DELETE FROM customer WHERE id = '" + customers_ticket.getText() +"'";

         connect=database.connectDB();

         try{
             Alert alert;

             statement=connect.createStatement();

             if(customers_ticket.getText().isEmpty() ||
                customers_movieTitle.getText().isEmpty() ||
                customers_boughtDate.getText().isEmpty() ||
                customers_boughtTime.getText().isEmpty()){

                 alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Please select the movie first.");
                 alert.showAndWait();
             }

             else{
                 alert=new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setTitle("Confirmation");
                 alert.setHeaderText(null);
                 alert.setContentText("Are you sure you want to delete "+customers_movieTitle.getText()+" ?");

                 Optional<ButtonType>option=alert.showAndWait();

                 if(option.get()==ButtonType.OK){
                     statement.executeUpdate(sql);

                     alert=new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Information");
                     alert.setHeaderText(null);
                     alert.setContentText("Successfully Removed");
                     alert.showAndWait();


                     clearCustomer();
                     showCustomerList();
                 }
                 else{
                     return;
                 }


             }


         }catch(Exception e){
             e.printStackTrace();
         }
     }




    //****************************************************************************************************//
    //*************************************Customer Part Finish******************************************//




    //****************************************************************************************************//
    //*************************************Available Movies Part******************************************//

    private SpinnerValueFactory<Integer>spin1;
    private SpinnerValueFactory<Integer>spin2;
    private SpinnerValueFactory<Integer>spin3;

    private float price1=0;
    private float price2=0;
    private float price3=0;
    private float total=0;
    private int qty=0;
    private int qty1=0;
    private int qty2=0;
    private int qty3=0;

    public void buy(){
        String sql="INSERT INTO customer (type,movieTitle,quantity,total,date,time) VALUES(?,?,?,?,?,?)";
        connect=database.connectDB();

        String type="";
        if(price1>0 && price2==0 && price3==0){
            type="VIP class";
        }

        else if(price2>0 && price2==0 && price3==0){
            type="Comfort class";
        }
        else if(price3>0 && price1==0 && price2==0){
            type="Saver class";
        }
        else if(price1>0 && price2>0 && price3>0){
            type="VIP, Comfort, Saver";
        }
        else if(price1==0 && price2>0 && price3>0){
            type="Comfort, Saver";
        }
        else if(price1>0 && price2>0 && price3==0){
            type="VIP, Comfort";
        }
        else if(price1>0 && price2==0 && price3>0){
            type="VIP, Saver";
        }

        Date date = new Date();
        java.sql.Date setDate=new java.sql.Date(date.getTime());

        try{

            LocalTime localtime=LocalTime.now();

            Time time=Time.valueOf(localtime);
            qty=qty1+qty2+qty3;

            prepare=connect.prepareStatement(sql);
            prepare.setString(1,type);
            prepare.setString(2,availableMovies_title.getText());
            prepare.setString(3,String.valueOf(qty));
            prepare.setString(4,String.valueOf(total));
            prepare.setString(5,String.valueOf(setDate));
            prepare.setString(6,String.valueOf(time));

            Alert alert;

            if(availableMovies_imageView.getImage()==null ||
                 availableMovies_movieTitle.getText().isEmpty()){

                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first.");
                alert.showAndWait();
            }

            else {
                prepare.executeUpdate();
                alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully purchase.");
                alert.showAndWait();

                String sql1="SELECT * FROM customer";

                prepare=connect.prepareStatement(sql1);
                result=prepare.executeQuery();

                int num = 0;

                while(result.next()){
                    num=result.getInt("id");
                }

                String sql2="INSERT INTO customer_info (customer_id,type,total,movieTitle) VALUES (?,?,?,?)";

                prepare=connect.prepareStatement(sql2);
                prepare.setString(1,String.valueOf(num));
                prepare.setString(2,type);
                prepare.setString(3,String.valueOf(total));
                prepare.setString(4,availableMovies_movieTitle.getText());

                prepare.execute();

                clearPurchaseTicketInfo();


            }
        }
       catch(Exception e){
           e.printStackTrace();
       }

    }

    public void clearPurchaseTicketInfo(){
        spin1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        spin2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        spin3=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);

        availableMovies_vip_quantity.setValueFactory(spin1);
        availableMovies_comfort_quantity.setValueFactory(spin2);
        availableMovies_saver_quantity.setValueFactory(spin3);

        availableMovies_vip_price.setText("0 Tk.");
        availableMovies_comfort_price.setText("0 Tk.");
        availableMovies_saver_price.setText("0 Tk.");
        availableMovies_total.setText("0 Tk.");


    }

    public void showSpinnerValue(){
        spin1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        spin2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);
        spin3=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,0);

        availableMovies_vip_quantity.setValueFactory(spin1);
        availableMovies_comfort_quantity.setValueFactory(spin2);
        availableMovies_saver_quantity.setValueFactory(spin3);

    }
    public void getSpinnerValue(){
        qty1=availableMovies_vip_quantity.getValue();
        qty2=availableMovies_comfort_quantity.getValue();
        qty3=availableMovies_saver_quantity.getValue();

        price1=(qty1*500);
        price2=(qty2*400);
        price3=(qty3*300);

        total=price1+price2+price3;

        availableMovies_vip_price.setText(String.valueOf(price1)+" Tk.");
        availableMovies_comfort_price.setText(String.valueOf(price3)+" Tk.");
        availableMovies_saver_price.setText(String.valueOf(price3)+" Tk.");

        availableMovies_total.setText(String.valueOf(total)+" Tk.");
    }

    public ObservableList<moviesData>availableMoviesList(){
        ObservableList<moviesData>listAvailMovies=FXCollections.observableArrayList();

        String sql="SELECT * FROM movie WHERE current = 'Showing'";
        connect=database.connectDB();

        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            moviesData mvD;

            while(result.next()){
                mvD=new moviesData(result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                listAvailMovies.add(mvD);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listAvailMovies;
    }

    private ObservableList<moviesData>availableMoviesList;
    public void showAvailableMovies(){
        availableMoviesList=availableMoviesList();

        availableMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showingDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableMovies_tableView.setItems(availableMoviesList);
    }

    public void selectAvailableMovies(){
        moviesData movdata=availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getSelectedIndex();

        if(num-1<-1){
            return;
        }
        availableMovies_title.setText(movdata.getTitle());
        availableMovies_genre.setText(movdata.getGenre());
        availableMovies_date.setText(String.valueOf(movdata.getDate()));

        getData.path=movdata.getImage();
        getData.title=movdata.getTitle();
    }

    public void selectMovie(){
        String uri="file:"+getData.path;

        image=new Image(uri,161,200,false,true);
        availableMovies_imageView.setImage(image);
        availableMovies_movieTitle.setText(getData.title);

        availableMovies_title.setText("");
        availableMovies_genre.setText("");
        availableMovies_date.setText("");
    }



    //****************************************************************************************************//
    //*************************************Available Movies Part finish******************************************//


    //****************************************************************************************************//
    //*************************************Edit Screening Part******************************************//

    private String[] currentList={"Showing","End Showing"};

    public void comboBox(){
        ArrayList<String>listCurrent=new ArrayList<>();

        for(String data:currentList){
            listCurrent.add(data);
        }

        ObservableList listC=FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }


    public ObservableList<moviesData>editScreeningList(){
        ObservableList<moviesData>editList=FXCollections.observableArrayList();

        String sql="SELECT * FROM movie";

        connect=database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            result=prepare.executeQuery();

            moviesData mD;

            while(result.next()){
                mD=new moviesData(result.getInt("id"),
                         result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));

                editList.add(mD);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return editList;
    }

    private ObservableList<moviesData>editScreeningLst;
    public void showEditScreening(){
       editScreeningLst=editScreeningList();

       editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        addScreening_tableView.setItems(editScreeningLst);
    }

    public void updateEditScreening(){
        String sql="UPDATE movie SET current = '"
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "' WHERE movieTitle = '" + editScreening_MovieTitle.getText() + "'";

        connect=database.connectDB();

        Alert alert;
        try{
            statement= connect.createStatement();
            if(editScreening_MovieTitle.getText().isEmpty() ||
              editScreening_imageView.getImage()==null ||
              editScreening_current.getSelectionModel().isEmpty()){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first.");
                alert.showAndWait();

            }
            else{
                statement.executeUpdate(sql);
                alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated");
                alert.showAndWait();
                showEditScreening();
                clearEditScreening();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clearEditScreening(){
          editScreening_imageView.setImage(null);
          editScreening_MovieTitle.setText("");
          //editScreening_current.setSelectionModel(null);
    }

    public void searchEditScreening(){
        //will do later
    }

    public void selectEditScreening(){
        moviesData md=addScreening_tableView.getSelectionModel().getSelectedItem();
        int number=addScreening_tableView.getSelectionModel().getFocusedIndex();

        if(number-1<-1){
            return;
        }

        String uri="file:"+md.getImage();
        image=new Image(uri,173,213,false,true);
        editScreening_imageView.setImage(image);
        editScreening_MovieTitle.setText(md.getTitle());
    }



    //****************************************************************************************************//
    //*************************************Edit Screening Part finish******************************************//


//****************************************************************************************************//
    //*************************************Add Movies Part******************************************//
    public void movieId(){
        String sql="SELECT count(id) FROM movie";
        connect=database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result=prepare.executeQuery();

            if(result.next()){
                getData.ticket=result.getInt("count(id)");
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }



    public ObservableList<moviesData>addMoviesList(){
        ObservableList<moviesData>listData= FXCollections.observableArrayList();
        String sql="SELECT * FROM movie";
        connect=database.connectDB();

        try{
             prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();

             moviesData mD;
             while(result.next()){
                 mD=new moviesData(result.getInt("id"),result.getString("movieTitle"),result.getString("genre"), result.getString("duration"),result.getString("image"),result.getDate("date") , result.getString("current"));
                 listData.add(mD);
             }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<moviesData>listAddMovies;

    public void showAddMoviesList(){
         listAddMovies=addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);
    }


    public void deleteAddMovies(){
        String sql="DELETE FROM movie WHERE movieTitle= '"
                +addMovies_movieTitle.getText()+"'";
        connect =database.connectDB();

        try{
            statement=connect.createStatement();
            Alert alert;
            if(addMovies_movieTitle.getText().isEmpty() ||
                    addMovies_genre.getText().isEmpty() ||
                    addMovies_duration.getText().isEmpty() ||
                    addMovies_imageView.getImage()==null){

                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first.");
                alert.showAndWait();

            }
            else{
                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message.");
                alert.setContentText("Are you really want to delete "+addMovies_movieTitle.getText()+" ?");
                alert.setHeaderText(null);
                Optional<ButtonType> option=alert.showAndWait();

                if(ButtonType.OK.equals(option.get())){
                    statement.executeUpdate(sql);

                    showAddMoviesList();
                    clearAddMoviesList();

                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted.");
                    alert.showAndWait();
                }
                else{
                    return;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void searchAddMovies(){
        //will do later
    }



    public void selectAddMoviesList(){
        moviesData movD=addMovies_tableView.getSelectionModel().getSelectedItem();
        int num=addMovies_tableView.getSelectionModel().getSelectedIndex();
        if(num-1<-1){
            return;
        }

        addMovies_movieTitle.setText(movD.getTitle());
        addMovies_genre.setText(movD.getGenre());
        addMovies_duration.setText(movD.getDuration());

        String getDate=String.valueOf(movD.getDate());


        String uri="file:"+movD.getImage();
        image=new Image(uri,148,200,false,true);
        addMovies_imageView.setImage(image);


    }

    public void importImage(){
        FileChooser open=new FileChooser();
        open.setTitle("Open Image File");

        Stage stage=(Stage)addMovies_form.getScene().getWindow();
        File file=open.showOpenDialog(stage);

        if(file!=null){
            image = new Image(file.toURI().toString(),148,200,false,true);
            addMovies_imageView.setImage(image);
            getData.path=file.getAbsolutePath();
        }
    }

    public void insertAddMovies(){

        String sql1="SELECT * FROM movie WHERE movieTitle='" + addMovies_movieTitle.getText() + "'";
        connect=database.connectDB();

        Alert alert;
        try {
            statement = connect.createStatement();
            result=statement.executeQuery(sql1);

            if(result.next()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + " was already exist.");
                alert.showAndWait();

            }

            else{
                 if(addMovies_movieTitle.getText().isEmpty() ||
                         addMovies_genre.getText().isEmpty() ||
                 addMovies_duration.getText().isEmpty() ||
                 addMovies_imageView.getImage()==null ||
                 addMovies_date.getValue()==null)
                 {
                     alert=new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Error");
                     alert.setHeaderText(null);
                     alert.setContentText("Please fill all blank fields.");
                     alert.showAndWait();
                 }
                 else{
                     String sql="INSERT INTO movie (id, movieTitle,genre,duration,image,date,current) VALUES (?,?,?,?,?,?,?)";

                     String uri=getData.path;
                     uri=uri.replace("\\","\\\\");

                     movieId();

                     String mID=String.valueOf(getData.movieId+1);



                     prepare = connect.prepareStatement(sql);
                     prepare.setString(1,mID);
                     prepare.setString(2,addMovies_movieTitle.getText());
                     prepare.setString(3,addMovies_genre.getText());
                     prepare.setString(4,addMovies_duration.getText());
                     prepare.setString(5,uri);
                     prepare.setString(6,String.valueOf(addMovies_date.getValue()));
                     prepare.setString(7,"Showing");

                     prepare.execute();

                     alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Information");
                     alert.setHeaderText(null);
                     alert.setContentText("Successfully added new Movie.");
                     alert.showAndWait();

                     clearAddMoviesList();
                     showAddMoviesList();
                 }
            }

        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateAddMovies(){
        //will do later
    }

    public void clearAddMoviesList(){
        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);
    }

    //****************************************************************************************************//
    //*************************************Add Movies Part finish******************************************//


    //****************************************************************************************************//
    //*************************************Switch Form Part******************************************//

    public void switchForm(ActionEvent event) {
        if(event.getSource()==dashboard_btn){
            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);
        }

        else if(event.getSource()==addMovies_btn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);
        }

        else if(event.getSource()==availableMovies_btn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            showAvailableMovies();
        }

        else if(event.getSource()==editScreening_btn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);
        }

        else if(event.getSource()==customers_btn){
            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);

            showCustomerList();
        }
    }

    //****************************************************************************************************//
    //*************************************Switch Form Part finish******************************************//



    //****************************************************************************************************//
    //*************************************Logout Part******************************************//

    public void logout(){
        try {
            signout.getScene().getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("/Admin_Login_Page.fxml"));

            Stage stage=new Stage();
            Scene scene=new Scene(root);

            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //****************************************************************************************************//
    //*************************************Logout Part finish******************************************//


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

          showAddMoviesList();
          showEditScreening();
          comboBox();
          showAvailableMovies();
          showSpinnerValue();
          showCustomerList();
          displayTotalSoldTicket();
          displayTotalIncomeToday();
          displayTotalAvailableovies();
    }


}
