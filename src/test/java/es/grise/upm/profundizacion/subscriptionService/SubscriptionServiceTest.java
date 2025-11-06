package es.grise.upm.profundizacion.subscriptionService;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.grise.upm.profundizacion.exceptions.ExistingUserException;
import es.grise.upm.profundizacion.exceptions.LocalUserDoesNotHaveNullEmailException;
import es.grise.upm.profundizacion.exceptions.NullUserException;


public class SubscriptionServiceTest {

    private SubscriptionService service;

    @Mock
    private User userMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SubscriptionService(Delivery.LOCAL); 
    }


    @Test(expected = NullUserException.class)
    public void testAddSubscriberThrowsNullUserExceptionWhenUserIsNull() throws Exception {
        service.addSubscriber(null);
    }


    @Test(expected = ExistingUserException.class)
    public void testAddSubscriberThrowsExistingUserExceptionWhenAlreadyExists() throws Exception {
        when(userMock.getDelivery()).thenReturn(Delivery.DO_NOT_DELIVER);
        when(userMock.getEmail()).thenReturn("test@example.com");

        service.addSubscriber(userMock); 
        service.addSubscriber(userMock); 
    }

   
    @Test(expected = LocalUserDoesNotHaveNullEmailException.class)
    public void testAddSubscriberThrowsLocalUserDoesNotHaveNullEmailException() throws Exception {
        when(userMock.getDelivery()).thenReturn(Delivery.LOCAL);
        when(userMock.getEmail()).thenReturn("local@example.com");

        service.addSubscriber(userMock);
        
    }
}
