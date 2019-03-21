import React, { useEffect } from 'react';

const Login = ({ requestLogin, loginErrorMsg, setLoginError, loginWithGoogle }) => {
  useEffect(
    () => () => {
      setLoginError('');
    },
    [],
  );

  const handleSubmit = event => {
    event.preventDefault();
    const { loginemail, loginpass } = event.target;
    const emailRegex = /^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+$/;
    if (loginemail.value.trim().length > 0 && loginpass.value.trim().length > 0) {
      if (emailRegex.test(loginemail.value)) {
        requestLogin({ email: loginemail.value, password: loginpass.value });
        event.target.reset();
        setLoginError('');
      } else {
        setLoginError('Wrong e-mail format!');
      }
    } else {
      setLoginError('Fill out all fields please!');
    }
  };

  const handleGoogleLogin = () => {
    console.log('hello from google');
    loginWithGoogle();
  };

  return (
    <form className="loginform" onSubmit={handleSubmit}>
      <h2>log in to your account</h2>
      {loginErrorMsg !== '' ? <h3>{loginErrorMsg}</h3> : null}
      <div>
        <input className="input" name="loginemail" type="text" id="loginemail" />
        <label htmlFor="loginemail">e-mail</label>
      </div>
      <div>
        <input name="loginpass" type="password" id="loginpass" />
        <label htmlFor="loginpass">password</label>
      </div>
      <button className="button" type="submit">
        <span>sign me in</span>
      </button>
      <p>or</p>
      <button type="button" className="googlesigninbtn" onClick={handleGoogleLogin}>
        <span>
          <i className="fab fa-google-plus-g" />
        </span>
        <span>Sign in with Google</span>
      </button>
    </form>
  );
};

export default Login;
