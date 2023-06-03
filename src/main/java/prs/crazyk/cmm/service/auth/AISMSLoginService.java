//package prs.crazyk.cmm.service.auth;
//
//import egovframework.let.uat.uia.service.impl.LoginDAO;
//import lombok.RequiredArgsConstructor;
//import net.miraeit.cmm.repository.AuthRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * 자꾸 잊어서 적어두는데 spring security 에서 알아서 가져다 쓴다
// * AuthenticationManager 동작을 보면 내부에서 가져다 사용한다
// * 컨트롤러에 없다고 재확인 좀 그만...
// */
//@Service
//@RequiredArgsConstructor
//public class AISMSLoginService implements UserDetailsService {
//    private final AuthRepository authRepository;
//    private final LoginDAO loginDAO;
//
//    @Override
//    public UserDetails loadUserByUsername(String emplyrId) throws UsernameNotFoundException {
//        return authRepository.selectUserByEmplyrId(emplyrId);
//    }
//}
