package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.addressDao;
import com.upgrad.FoodOrderingApp.service.dao.customerDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AddressService {
    @Autowired
    private static addressDao add;
    private static customerDao user;
    public static AddressEntity saveAddress(AddressEntity a,CustomerEntity c)throws SaveAddressException{
        if(a.getCity().length()==0||a.getFlatBuilNo().length()==0||a.getLocality().length()==0||a.getUuid().length()==0||a.getPincode().length()==0)
            throw new SaveAddressException("SAR-001","No field can be empty");
        if(!isPincodeValid(a.getPincode()))
            throw new SaveAddressException("SAR-002","Invalid pincode");
        if(getStateByUUID(a.getUuid())==null)
            throw new SaveAddressException("ANF-002","No state by this id");
        else
            a.setState(getStateByUUID(a.getUuid()));
        a=add.addAddress(a);
        return a;
    }
    public static boolean isPincodeValid(String pin){
        Pattern p = Pattern.compile("[0-9]{6}");
        if(pin.length()!=6)
            return false;
        return p.matcher(pin).matches();
    }
    public static StateEntity getStateByUUID(String uuid){
        StateEntity s=add.getState(uuid);
        return s;
    }
    public static List<AddressEntity> getAllAddress(CustomerEntity c){
        return addressDao.getAllAddress(c);
    }
    public static AddressEntity deleteAddress(AddressEntity id)throws AddressNotFoundException{
        if(id.getUuid().length()==0)
            throw new AddressNotFoundException("ANF-005","Address id can not be empty");
        return addressDao.delete(id);

    }
    public static AddressEntity getAddressByUUID(String id,CustomerEntity c)throws AddressNotFoundException{
        return addressDao.getAddress(id);
    }

}
