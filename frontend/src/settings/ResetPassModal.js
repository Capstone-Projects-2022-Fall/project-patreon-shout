import * as React from 'react';
import { Stack, Box, Typography, Button, Modal, TextField } from '@mui/material';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

function ResetPassModal() {

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <div>
            <Typography m={2} align="center">
                <Button variant="contained" onClick={handleOpen}>Reset Password</Button>
            </Typography>

            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>

                    <Typography variant="h6" component="h3" sx={{ mt: 1 }}>
                        Update your password
                    </Typography>

                    <Typography component="h2" sx={{ mt: 3 }}>
                        <TextField fullWidth id="outlined-basic" label="Current Password" type="password" variant="outlined" />
                    </Typography>

                    <Typography component="h2">
                        <TextField fullWidth id="outlined-basic" label="New Password" type="password" variant="outlined" />
                    </Typography>

                    <Typography component="h2">
                        <TextField fullWidth id="outlined-basic" label="Confirm New Password" type="password" variant="outlined" />
                    </Typography>

                    <Box sx={{ flexGrow: 1 }} mt={2}>
                        <Stack direction="row" spacing={21}>
                            <Button variant="text" color='error' onClick={handleClose}>cancel</Button>
                            <Button variant="contained">Submit</Button>
                        </Stack>
                    </Box>
                </Box>
            </Modal>
        </div>
    );
}

export default ResetPassModal;