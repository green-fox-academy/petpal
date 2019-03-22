import React, { useEffect } from 'react';

const Register = ({ requestRegister, setRegisterError, registerErrorMsg }) => {
  useEffect(
    () => () => {
      setRegisterError('');
    },
    [],
  );

  const handlesubmit = event => {
    event.preventDefault();
    const { registername, registerpass, registeremail } = event.target;
    const emailRegex = /^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+$/;
    const nameRegex = /^[a-záéíűúőóüöA-ZÁÍÉŰÚŐÓÜÖ -]*$/;
    if ((registername.value.trim().length > 0 && registerpass.value.trim().length > 0, registeremail.value.trim().length > 0)) {
      if (nameRegex.test(registername.value)) {
        if (emailRegex.test(registeremail.value)) {
          requestRegister({ username: registername.value, password: registerpass.value, registeremail: registeremail.value });
          setRegisterError('');
          event.target.reset();
        } else {
          setRegisterError('Wrong e-mail format!');
        }
      } else {
        setRegisterError('Name can only contain letters and spaces!');
      }
    } else {
      setRegisterError('Fill out all fields please!');
    }
  };

  return (
    <form className="registerform" onSubmit={handlesubmit}>
      <h2>sign up for free</h2>
      {registerErrorMsg ? <h3>{registerErrorMsg}</h3> : null}
      <div>
        <input className="input" name="registername" type="text" id="registername" />
        <label htmlFor="registername">username</label>
      </div>
      <div>
        <input className="input" name="registeremail" type="text" id="registeremail" />
        <label htmlFor="registeremail">e-mail</label>
      </div>
      <div>
        <input name="registerpass" type="password" id="registerpass" />
        <label htmlFor="registerpass">password</label>
      </div>
      <button className="button" type="submit">
        <span>register</span>
      </button>
    </form>
  );
};

export default Register;
