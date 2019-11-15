package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName("Smith").build());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        Assert.assertEquals(ownerId,owner.getId());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        Assert.assertEquals(1,ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        Assert.assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        Assert.assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long ownerId2 = 2L;
        Owner owner2 = Owner.builder().id(ownerId2).build();
        Owner savedOwner = ownerMapService.save(owner2);

        Assert.assertEquals(ownerId2,savedOwner.getId());

    }

    @Test
    void saveNullId(){
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        Assert.assertNotNull(savedOwner);
        Assert.assertNotNull(savedOwner.getId());
    }


    @Test
    void findByLastnameNotNull() {
        Owner owner = ownerMapService.findByLastName("Smith");

        Assert.assertNotNull(owner);
        Assert.assertEquals(ownerId,owner.getId());
    }

    @Test
    void findByLastnameEqualsNull() {
        Owner owner = ownerMapService.findByLastName("Foo");

        Assert.assertNull(owner);

    }
}