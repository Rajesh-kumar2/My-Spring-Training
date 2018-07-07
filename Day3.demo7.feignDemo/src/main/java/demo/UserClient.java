package demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="fiegndemo", url="https://reqres.in/")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/users/2")
    UserData getUser();
}