package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper<T> {
        T map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Object> map(List<Person> personList, PersonToAccountMapper mapper) {
        // TODO implémenter la méthode pour transformer une liste de personnes en liste de comptes
        return personList.stream().map(p -> mapper.map(p)).collect(Collectors.toList());
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List result = map(personList, new PersonToAccountMapper<Account>() {
			
			@Override
			public Account map(Person p) {
				// TODO Auto-generated method stub
				Account a = new Account();
				a.setOwner(p);
				a.setBalance(100);
				return a;
			}
		});

        assert result.size() == personList.size();
        for (Account account : (List<Account>)result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        
        // TODO transformer la liste de personnes en liste de prénoms
        List result = map(personList, new PersonToAccountMapper() {
			
			@Override
			public String map(Person p) {
				// TODO Auto-generated method stub
				return p.getFirstname();
			}
		});

        assert result.size() == personList.size();
        for (String firstname : (List<String>)result) {
            assert firstname.startsWith("first");
        }
    }
    // end::test_map_person_to_firstname[]
}
