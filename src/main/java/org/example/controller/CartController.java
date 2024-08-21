package org.example.controller;

import org.example.dto.CartDto;
import org.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public boolean addCart(@RequestBody CartDto cartDto){
        return cartService.addCart(cartDto);
    }

    @GetMapping("/getAll")
    public List<CartDto> getAllCartDetails(){
        return cartService.getAllCartDetails();
    }

    @PutMapping("/update/add/{id}")
    public Boolean updateCart(@PathVariable Long id) {
        return cartService.updateAddCart(id);
    }

    @PutMapping("/update/sub/{id}")
    public Boolean updateSubCart(@PathVariable Long id){
        return cartService.updateSubCart(id);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean updateStatus(@PathVariable long id){
        return cartService.deleteItemById(id);
    }

    @GetMapping("/get/{id}")
    public CartDto getCartById(@PathVariable long id){
        return cartService.getCartById(id);
    }

    @GetMapping("/get/customer/{id}")
    public List<CartDto> getCartByCustomer(@PathVariable long id){
        return cartService.getCartByCustomer(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String, String> map=new HashMap<>();
        List<ObjectError> list=exception.getBindingResult().getAllErrors();
        for(ObjectError item:list) {
            FieldError fieldError=(FieldError) item;
            String fieldName= fieldError.getField();
            String message = item.getDefaultMessage();
            map.put(fieldName,message);
        }
        return map;
    }
}
