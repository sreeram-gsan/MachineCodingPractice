import constant.Gender;
import constant.Pincode;
import exception.QuantityNotAvailableException;
import exception.RestaurantNotFoundException;
import exception.UserNotFoundException;
import model.Restaurant;
import model.User;
import service.RestaurantService;
import service.UserService;

public class Driver {
    public static void main (String args[]){

        UserService userService = UserService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();

        User user1 = userService.registerUser("9998887776","User1", Gender.MALE, Pincode.BTM);
        User user2 = userService.registerUser("9998887775","User2", Gender.MALE, Pincode.BTM);
        User user3 = userService.registerUser("9998887774","User3", Gender.MALE, Pincode.HSR);
        try{
            userService.login("9998887776");
        }catch (UserNotFoundException e){
            System.out.println("Unable to login: User not found");
        }
        Restaurant restaurant1 = restaurantService.registerRestaurant("Res1","HSR/BTM","Burger",100,10);
        Restaurant restaurant2 = restaurantService.registerRestaurant("Res2","HSR","Burger",10,10);
        Restaurant restaurant3 = restaurantService.registerRestaurant("Res3","BTM","Burger",50,10);
        Restaurant restaurant4 = restaurantService.registerRestaurant("Res4","HSR/BTM","Burger",70,10);
        restaurantService.updateQuantity("Res1",15);
        restaurantService.addRating("Res1",5,null);
        restaurantService.addRating("Res1",4,null);
        restaurantService.addRating("Res1",3,null);
        restaurantService.addRating("Res3",5,null);
        System.out.println("List of rest: ");
        for (Restaurant r: userService.showRestaurents(restaurantService,"rating")){
            System.out.println(r.getName()+ "Quantity: "+r.getAvailableQuantity());
        }

        try{
            restaurantService.placeOrder("Res1",10);
        } catch (QuantityNotAvailableException e) {
            System.out.println("Requested Quantity not available");
        } catch (RestaurantNotFoundException e) {
            System.out.println("Restaurant not found");
        }

        try{
            restaurantService.placeOrder("Res1",15);
        } catch (QuantityNotAvailableException e) {
            System.out.println("Requested Quantity not available");
        } catch (RestaurantNotFoundException e) {
            System.out.println("Restaurant not found");
        }
    }
}
