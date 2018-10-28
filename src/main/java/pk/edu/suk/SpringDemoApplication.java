package pk.edu.suk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pk.edu.suk.model.Role;
import pk.edu.suk.model.User;
import pk.edu.suk.service.RoleService;
import pk.edu.suk.service.UserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDemoApplication implements CommandLineRunner{


	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;



	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		List<Role> roles = new ArrayList<>();

		Role roleAdmin = new Role("ADMIN");
		Role roleUser = new Role("USER");;

		roleService.save(roleAdmin);
		roleService.save(roleUser);

		roles.add(roleAdmin);

		User user = new User("Arshad Ali", "Soomro", "arshadalisoomro7@gmail.com", "Some123@pass", roles);

		userService.save(user);

	}
}
