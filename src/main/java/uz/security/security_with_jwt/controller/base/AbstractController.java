package uz.security.security_with_jwt.controller.base;

import lombok.RequiredArgsConstructor;
import uz.security.security_with_jwt.service.base.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    
    protected final S service;
    
}
