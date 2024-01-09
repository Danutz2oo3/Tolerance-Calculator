import { Component, OnInit, AfterViewInit, ElementRef, Output, EventEmitter } from '@angular/core';
import {LoginService} from '../services/login.service';
import {AbstractControl, FormBuilder, FormGroup, Validators, ValidationErrors, ValidatorFn} from "@angular/forms";
import {UserService} from "../services/user.service";
import {User} from "../model/user.model";
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit, AfterViewInit {

  container: HTMLElement | null = null;
  registerBtn: HTMLElement | null = null;
  loginBtn: HTMLElement | null = null;

  loginForm!: FormGroup;
  signUpForm!: FormGroup;

  signUpButtonClicked: boolean  = false;

  constructor(private el: ElementRef,
              private ts: LoginService,
              private formBuilder: FormBuilder,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      emailOrUsername: [''],
      password: [''],
    });
    this.signUpForm = this.formBuilder.group({
      username: [''],
      email: ['', [Validators.required, this.emailValidator]],
      password: [''],
      confirmPassword: [''],
    },{validator: passwordMatchValidator});

  }

  ngAfterViewInit(): void {
    this.container = this.el.nativeElement.querySelector('#container');
    this.registerBtn = this.el.nativeElement.querySelector('#register');
    this.loginBtn = this.el.nativeElement.querySelector('#login');

    if (this.registerBtn && this.loginBtn) {
      this.registerBtn.addEventListener('click', () => {
        if (this.container) {
          this.container.classList.add('active');
        }
      });

      this.loginBtn.addEventListener('click', () => {
        if (this.container) {
          this.container.classList.remove('active');
        }
      });
    }
  }

  onSignInClick(): void {
    const {emailOrUsername, password} = this.loginForm.value;
    console.log('Request Payload:', { emailOrUsername, password });
    if (/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i.test(emailOrUsername)) {
      console.log('Email sign-in:', emailOrUsername);
      this.ts.loginEmail(emailOrUsername, password).subscribe({
        next: (data) => {
          console.log(data);
          console.log('success');
          this.onSignInSuccess(data);
          this.signIn.emit();
        },
        error: (error) => {
          console.log(error);
        }
      });
    } else if (/^[a-zA-Z0-9]+$/.test(emailOrUsername)) {
      console.log('Username sign-in:', emailOrUsername);
      this.ts.loginUsername(emailOrUsername, password).subscribe({
        next: (data) => {
          console.log(data);
          console.log('success');
          this.onSignInSuccess(data);
          this.signIn.emit();
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
    this.clearForm();
  }

  onSignUpClick(): void {
    this.signUpButtonClicked = true;
    const {username, email, password, confirmPassword} = this.signUpForm.value;
    console.log('Request Payload:', { username, email, password, confirmPassword });
    if(this.signUpForm.valid) {
      this.ts.register(username, email, password).subscribe({
           next: (data) => {
              console.log(data);
              console.log('success');
             if (this.container) {
               this.container.classList.remove('active');
             }
            },
            error: (error) => {
              console.log(error);
              if(error.error.code === 400 && error.error.message === 'user already exists') {
                console.log('User already exists');
              }
              else if(error.status === 400 && error.error.message === 'username already exists') {
                console.log('Username already exists');
              }
              else if(error.status === 400 && error.error.message === 'email already exists') {
                console.log('Email already exists');
              }
            }
        });
    }
    this.clearForm();
  }

  @Output() signIn: EventEmitter<void> = new EventEmitter<void>();
  @Output() signUp: EventEmitter<void> = new EventEmitter<void>();

  emailValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i;

    if (control.value && !emailPattern.test(control.value)) {
      console.log('invalid email');
      return {'invalidEmail': true};
    }
    return null;
  }
  onSignInSuccess(userData: User): void {
    this.userService.setUserData(userData);
  }
  clearForm(): void {
    this.loginForm.reset();
    this.signUpForm.reset();
  }
}
export const passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');

  if (!password || !confirmPassword || password.value === confirmPassword.value) {
    return null; // Passwords match
  } else {
    return { passwordMismatch: true }; // Passwords do not match
  }
};
