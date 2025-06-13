package springboot.projetfinal.model;

public class JsonViews {

    public static class Common{
    }
    public static class CustomerWithAddress extends Common{

    }
    public static class AddressWithCustomer extends Common{
    }

    public static class CustomerWithItems extends Common{
    }
    public static class ItemWithCustomer extends Common{
    }

    public static class ItemWithCategory extends Common{
    }
    public static class CategoryWithItem extends Common{
    }

    public static class ItemWithIngredients extends Common{
    }
    public static class IngredientWithItems extends Common{
    }

    public static class CustomerWithReservations extends Common{
    }
    public static class ReservationWithCustomer extends Common{
    }

    public static class CustomerWithOrders extends Common{}
    public static class OrderWithCustomer extends Common{}

    public static class OrderWithOrderLines extends Common{}
    public static class OrderLineWithOrder extends Common{}

    public static class OrderLineWithItem extends Common{}
    public static class ItemWithOrderLine extends Common{}

    public static class OrderWithStatus extends Common{}
    public static class StatusWithOrder extends Common{}

    public static class ReservationWithSlot extends Common{}
    public static class SlotWithReservation extends Common{}

}
