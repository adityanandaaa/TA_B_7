package apap.ta.ruangan.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/add-user-form").hasAnyAuthority("Admin TU")
                .antMatchers("/add-user-detail").hasAnyAuthority("Admin TU")
                .antMatchers("/pengadaan-fasilitas/").hasAnyAuthority("Admin TU", "Guru")
                .antMatchers("/fasilitas/add").hasAnyAuthority("Admin TU")
                .antMatchers("/fasilitas/pengadaanBuku").hasAnyAuthority("Admin TU")
                .antMatchers("/ruangan/ubah-jumlah-fasilitas**").hasAnyAuthority("Admin TU")
                .antMatchers("/ruangan/hapus-fasilitas**").hasAnyAuthority("Admin TU")
                .antMatchers("/peminjaman-ruangan/ubah-persetujuan**").hasAnyAuthority("Admin TU")
                .antMatchers("/peminjaman-ruangan/peminjaman-ruangan-all").hasAnyAuthority("Admin TU","Guru","Siswa")
                .antMatchers("/peminjaman-ruangan/tambah").hasAnyAuthority("Siswa","Guru")
                .antMatchers("/api/v1/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login").permitAll()

                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthertication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

//    @Autowired
//    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder())
//                .withUser("nadiem").password(encoder().encode("makarim"))
//                .roles("Admin TU");
//    }

}
